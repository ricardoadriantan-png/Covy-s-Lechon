package com.example.coyvsplashscreen;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CreateAccount extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_create_account);

        Button btnSignUp = findViewById(R.id.btnSignUp);
        TextView tvAlreadyHaveAccount = findViewById(R.id.tvAlreadyHaveAccount);

        if (btnSignUp != null) {
            btnSignUp.setOnClickListener(v -> {
                Toast.makeText(CreateAccount.this, "Account Created Successfully!", Toast.LENGTH_SHORT).show();
                navigateToSignIn();
            });
        }

        if (tvAlreadyHaveAccount != null) {
            tvAlreadyHaveAccount.setOnClickListener(v -> navigateToSignIn());
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void navigateToSignIn() {
        Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.example.covysigninscreen");
        if (launchIntent != null) {
            startActivity(launchIntent);
        } else {
            Intent intent = new Intent(CreateAccount.this, SignInActivity.class);
            startActivity(intent);
        }
        finish();
    }
}