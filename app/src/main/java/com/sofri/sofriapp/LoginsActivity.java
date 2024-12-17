package com.sofri.sofriapp;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.sofri.sofriapp.R;

import java.util.concurrent.TimeUnit;

public class LoginsActivity extends AppCompatActivity {

    private EditText phoneNumberEditText, otpEditText;
    private Button sendOtpButton, loginButton;

    private FirebaseAuth mAuth;
    private String verificationId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logins);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        // Initialize views
        phoneNumberEditText = findViewById(R.id.phoneNumber);
        otpEditText = findViewById(R.id.otpCode);
        sendOtpButton = findViewById(R.id.sendOtpButton);
        loginButton = findViewById(R.id.loginButton);

        // Send OTP Button Logic
        sendOtpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = phoneNumberEditText.getText().toString().trim();
                if (TextUtils.isEmpty(phoneNumber)) {
                    Toast.makeText(LoginsActivity.this, "Enter phone number", Toast.LENGTH_SHORT).show();
                } else {
                    sendOtp(phoneNumber);
                }
            }
        });

        // Login Button Logic
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String otpCode = otpEditText.getText().toString().trim();
                if (TextUtils.isEmpty(otpCode)) {
                    Toast.makeText(LoginsActivity.this, "Enter OTP", Toast.LENGTH_SHORT).show();
                } else {
                    verifyOtp(otpCode);
                }
            }
        });
    }

    private void sendOtp(String phoneNumber) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "+255" + phoneNumber,    // Phone number (Tanzania country code)
                60,                      // Timeout duration
                TimeUnit.SECONDS,        // Unit of timeout
                this,                    // Activity (context)
                new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    @Override
                    public void onVerificationCompleted(@NonNull PhoneAuthCredential credential) {
                        // Auto-retrieval or instant verification
                        signInWithCredential(credential);
                    }

                    @Override
                    public void onVerificationFailed(@NonNull FirebaseException e) {
                        Toast.makeText(LoginsActivity.this, "Verification Failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCodeSent(@NonNull String verificationId, @NonNull PhoneAuthProvider.ForceResendingToken token) {
                        LoginsActivity.this.verificationId = verificationId;
                        Toast.makeText(LoginsActivity.this, "OTP Sent", Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }

    private void verifyOtp(String otpCode) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, otpCode);
        signInWithCredential(credential);
    }

    private void signInWithCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Toast.makeText(LoginsActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                // Proceed to next screen or dashboard
            } else {
                Toast.makeText(LoginsActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
