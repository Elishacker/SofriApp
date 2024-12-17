package com.sofri.sofriapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button btnContinueWithPhone;
    private Button btnSignAsGuest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize buttons
        btnContinueWithPhone = findViewById(R.id.phone_no);
        btnSignAsGuest = findViewById(R.id.btn_sign_as_guest);

        // "Continue with Phone" button logic
        btnContinueWithPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirect to LoginActivity
                Intent loginIntent = new Intent(MainActivity.this, LoginsActivity.class);
                startActivity(loginIntent);
            }
        });

        // "Sign in as Guest" button logic
        btnSignAsGuest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirect to Dashboard
                Intent dashboardIntent = new Intent(MainActivity.this, Dashbard.class);
                startActivity(dashboardIntent);
                Toast.makeText(MainActivity.this, "Signed in as Guest", Toast.LENGTH_SHORT).show();
            }
        });
    }
}