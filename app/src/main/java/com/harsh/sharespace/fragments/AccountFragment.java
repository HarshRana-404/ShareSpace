package com.harsh.sharespace.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.harsh.sharespace.R;

public class AccountFragment extends Fragment {

    private TextView tvUsername, tvEmail, tvMobile, tvAddress, tvCity, tvState;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;

    public AccountFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragAccount = inflater.inflate(R.layout.fragment_account, container, false);
        tvUsername = fragAccount.findViewById(R.id.tv_username);
        tvEmail = fragAccount.findViewById(R.id.tv_email);
        tvMobile = fragAccount.findViewById(R.id.tv_mobile);
        tvAddress = fragAccount.findViewById(R.id.tv_address);
        tvCity = fragAccount.findViewById(R.id.tv_city);
        tvState = fragAccount.findViewById(R.id.tv_state);
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        loadUserData();

        return fragAccount;
    }

    private void loadUserData() {
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            String userId = currentUser.getUid();

            db.collection("users").document(userId)
                    .get()
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                tvUsername.setText(document.getString("username"));
                                tvEmail.setText(document.getString("email"));
                                tvMobile.setText(document.getString("mobile"));
                                tvAddress.setText(document.getString("address"));
                                tvCity.setText(document.getString("city"));
                                tvState.setText(document.getString("state"));
                            } else {
                                Toast.makeText(getActivity(), "User data not found", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(getActivity(), "Error getting user data", Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }
}