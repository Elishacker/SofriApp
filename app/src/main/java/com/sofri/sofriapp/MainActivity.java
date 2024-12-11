package com.sofri.sofriapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button btnContinueWithMail;
    private Button btnSignAsGuest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Use the XML layout name

        // Initialize buttons
        btnContinueWithMail = findViewById(R.id.btn_continue_with_mail);
        btnSignAsGuest = findViewById(R.id.btn_sign_as_guest);

        // Set onClick listeners
        btnContinueWithMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to email sign-up activity
                Intent intent = new Intent(MainActivity.this, EmailSignupActivity.class);
                startActivity(intent);
            }
        });

        btnSignAsGuest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the main application as a guest
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
