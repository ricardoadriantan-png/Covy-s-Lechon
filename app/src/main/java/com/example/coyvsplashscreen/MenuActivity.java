package com.example.coyvsplashscreen;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.menu);

        Button btnAddWhole = findViewById(R.id.btn_add_whole);
        Button btnAddHalf = findViewById(R.id.btn_add_half);
        Button btnAddPaa = findViewById(R.id.btn_add_paa);
        Button btnAddWings = findViewById(R.id.btn_add_wings);
        Button btnAddInasal = findViewById(R.id.btn_add_inasal);

        if (btnAddWhole != null) {
            btnAddWhole.setOnClickListener(v -> Toast.makeText(this, "Added Lechon Manok (Whole) to Order!", Toast.LENGTH_SHORT).show());
        }
        if (btnAddHalf != null) {
            btnAddHalf.setOnClickListener(v -> Toast.makeText(this, "Added Lechon Manok (Half) to Order!", Toast.LENGTH_SHORT).show());
        }
        if (btnAddPaa != null) {
            btnAddPaa.setOnClickListener(v -> Toast.makeText(this, "Added Paa to Order!", Toast.LENGTH_SHORT).show());
        }
        if (btnAddWings != null) {
            btnAddWings.setOnClickListener(v -> Toast.makeText(this, "Added Wings to Order!", Toast.LENGTH_SHORT).show());
        }
        if (btnAddInasal != null) {
            btnAddInasal.setOnClickListener(v -> Toast.makeText(this, "Added Chicken Inasal to Order!", Toast.LENGTH_SHORT).show());
        }

        BottomNavigationView bottomNavigation = findViewById(R.id.bottom_navigation);
        if (bottomNavigation != null) {
            bottomNavigation.setSelectedItemId(R.id.nav_menu);
            bottomNavigation.setOnItemSelectedListener(item -> {
                int itemId = item.getItemId();
                if (itemId == R.id.nav_home) {
                    Intent intent = new Intent(MenuActivity.this, DashBoardActivity.class);
                    startActivity(intent);
                    finish();
                    return true;
                } else if (itemId == R.id.nav_profile) {
                    Intent intent = new Intent(MenuActivity.this, ProfileActivity.class);
                    startActivity(intent);
                    finish();
                    return true;
                } else if (itemId == R.id.nav_menu) {
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
    }
}