package com.example.fuelqueue;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fuelqueue.screen.station_owner.OwnerFuelTypeListActivity;

import java.util.Objects;

/**
 * Initiate the register screen
 */
public class RegisterActivity extends AppCompatActivity {
    /**
     * Initiate the UI
     *
     * @param savedInstanceState Bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_register);
        /* Locate UI in xml */
        Button btnLogin = (Button) findViewById(R.id.btn_register);
        /* Navigate to next screen */
        btnLogin.setOnClickListener(view -> {
            Intent intent = new Intent(view.getContext(), OwnerFuelTypeListActivity.class);
            startActivity(intent);
        });
    }
}