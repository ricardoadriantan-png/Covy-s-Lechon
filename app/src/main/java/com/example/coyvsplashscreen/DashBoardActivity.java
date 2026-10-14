package com.example.coyvsplashscreen;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class DashBoardActivity extends AppCompatActivity {

    private int currentQueueNumber = 42;
    private final int nowServingNumber = 39;

    private TextView tvQueueNumber;
    private TextView tvNowServing;
    private TextView tvWelcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dashboard);

        tvQueueNumber = findViewById(R.id.tv_queue_number);
        tvNowServing = findViewById(R.id.tv_now_serving);
        tvWelcome = findViewById(R.id.tv_welcome);

        // Display user's name from local SharedPreferences, Firebase Auth displayName, or email fallback
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        String userName = getSharedPreferences("CovyPrefs", MODE_PRIVATE).getString("user_name", "");

        if (userName.isEmpty() && currentUser != null && currentUser.getDisplayName() != null && !currentUser.getDisplayName().isEmpty()) {
            userName = currentUser.getDisplayName();
        }

        if (userName.isEmpty() && currentUser != null && currentUser.getEmail() != null) {
            String email = currentUser.getEmail();
            int atIndex = email.indexOf('@');
            if (atIndex > 0) {
                userName = email.substring(0, atIndex);
            }
        }

        if (userName.isEmpty()) {
            userName = "CUSTOMER";
        }

        if (tvWelcome != null) {
            tvWelcome.setText("WELCOME, " + userName.toUpperCase() + "!");
        }

        Button btnPlaceOrder = findViewById(R.id.btn_place_order);
        if (btnPlaceOrder != null) {
            btnPlaceOrder.setOnClickListener(v -> showPlaceOrderDialog());
        }

        BottomNavigationView bottomNavigation = findViewById(R.id.bottom_navigation);
        if (bottomNavigation != null) {
            bottomNavigation.setSelectedItemId(R.id.nav_home);
            bottomNavigation.setOnItemSelectedListener(item -> {
                int itemId = item.getItemId();
                if (itemId == R.id.nav_profile) {
                    Intent intent = new Intent(DashBoardActivity.this, ProfileActivity.class);
                    startActivity(intent);
                    return true;
                } else if (itemId == R.id.nav_home) {
                    return true;
                }
                return true;
            });
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        updateQueueDisplay();
    }

    private void showPlaceOrderDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Place New Order")
                .setMessage("Would you like to place a new order and generate a queue number?")
                .setPositiveButton("Confirm Order", (dialog, which) -> {
                    currentQueueNumber++;
                    updateQueueDisplay();
                    Toast.makeText(DashBoardActivity.this,
                            "Order Placed! Your Queue Number is #Q-" + String.format(Locale.US, "%03d", currentQueueNumber),
                            Toast.LENGTH_LONG).show();
                })
                .setNegativeButton("Cancel", null)
                .show();
    }

    private void updateQueueDisplay() {
        if (tvQueueNumber != null) {
            tvQueueNumber.setText(String.format(Locale.US, "#Q-%03d", currentQueueNumber));
        }
        if (tvNowServing != null) {
            int ahead = Math.max(0, currentQueueNumber - nowServingNumber);
            tvNowServing.setText(String.format(Locale.US, "Now Serving: #Q-%03d (%d ahead of you)", nowServingNumber, ahead));
        }
    }
}