package com.example.fuelqueue;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fuelqueue.screen.station_owner.UpdateFuelTypeActivity;

import java.util.Objects;

/**
 * Login screen
 */
public class LoginActivity extends AppCompatActivity {
    /**
     * Initiate the UI
     *
     * @param savedInstanceState Bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_login);
        /* Locate UI in xml */
        Button btnLogin = (Button) findViewById(R.id.btn_log_in);
        /* Navigate to next screen */
        btnLogin.setOnClickListener(view -> {
            Intent intent = new Intent(view.getContext(), UpdateFuelTypeActivity.class);
            startActivity(intent);
        });
    }
}