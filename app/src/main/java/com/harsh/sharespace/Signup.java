package com.harsh.sharespace;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Signup extends AppCompatActivity {

    private TextInputEditText etUsername, etEmail, etPassword, etMobile, etCity, etAddress;
    private AutoCompleteTextView etState;
    private Button btnSignUp;
    private TextView tvLogin;

    private FirebaseAuth mAuth;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        etUsername = findViewById(R.id.et_username);
        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);
        etMobile = findViewById(R.id.et_mobile);
        etCity = findViewById(R.id.et_city);
        etAddress = findViewById(R.id.et_address);
        etState = findViewById(R.id.et_state);
        btnSignUp = findViewById(R.id.btn_sign_up);
        tvLogin = findViewById(R.id.tv_login);
        // Populate the AutoCompleteTextView with state suggestions
        String[] states = getResources().getStringArray(R.array.states);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, states);
        etState.setAdapter(adapter);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etUsername.getText().toString().trim();
                String email = etEmail.getText().toString().trim();
                String password = etPassword.getText().toString().trim();
                String mobile = etMobile.getText().toString().trim();
                String city = etCity.getText().toString().trim();
                String address = etAddress.getText().toString().trim();
                String state = etState.getText().toString().trim();

                if (TextUtils.isEmpty(username)) {
                    etUsername.setError("Username is required.");
                    return;
                }

                if (TextUtils.isEmpty(email)) {
                    etEmail.setError("Email is required.");
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    etPassword.setError("Password is required.");
                    return;
                }

                if (TextUtils.isEmpty(mobile)) {
                    etMobile.setError("Mobile number is required.");
                    return;
                }

                if (TextUtils.isEmpty(city)) {
                    etCity.setError("City is required.");
                    return;
                }

                if (TextUtils.isEmpty(address)) {
                    etAddress.setError("Address is required.");
                    return;
                }

                if (TextUtils.isEmpty(state)) {
                    etState.setError("State is required.");
                    return;
                }

                registerUser(username, email, password, mobile, city, address, state);
            }
        });

        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to login activity
                finish();
            }
        });
    }

    private void registerUser(String username, String email, String password, String mobile, String city, String address, String state) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();

                            // Save additional user information to Firestore
                            Map<String, Object> userDetails = new HashMap<>();
                            userDetails.put("username", username);
                            userDetails.put("email", email);
                            userDetails.put("mobile", mobile);
                            userDetails.put("city", city);
                            userDetails.put("address", address);
                            userDetails.put("state", state);
                            db.collection("users").document(user.getUid())
                                    .set(userDetails)
                                    .addOnSuccessListener(aVoid -> {
                                        Toast.makeText(Signup.this, "Registration successful.", Toast.LENGTH_SHORT).show();
                                        updateUI(user);
                                    })
                                    .addOnFailureListener(e -> Toast.makeText(Signup.this, "Failed to save user details.", Toast.LENGTH_SHORT).show());
                        } else {
                            Toast.makeText(Signup.this, "Registration failed.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    private void updateUI(FirebaseUser user) {
        if (user != null) {
            // Navigate to main activity or dashboard
            finish();
        }
    }
}
