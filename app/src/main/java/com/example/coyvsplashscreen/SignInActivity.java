package com.example.coyvsplashscreen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SignInActivity extends AppCompatActivity {
    EditText username;
    EditText password;
    Button buttonlogin;
    Button buttonNewAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_in);

        username = findViewById(R.id.editTextTextEmailAddress);
        password = findViewById(R.id.editTextTextPassword);
        buttonlogin = findViewById(R.id.loginbutton);
        buttonNewAccount = findViewById(R.id.newaccountbutton);

        buttonlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userStr = username.getText().toString();
                String passStr = password.getText().toString();

                if (userStr.equals("users") && passStr.equals("1234")) {
                    Toast.makeText(SignInActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SignInActivity.this, DashBoardActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(SignInActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

        if (buttonNewAccount != null) {
            buttonNewAccount.setOnClickListener(v -> {
                Intent intent = new Intent(SignInActivity.this, CreateAccount.class);
                startActivity(intent);
            });
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}