package com.example.fuelqueue;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.fuelqueue.screen.station_owner.OwnerFuelTypeListActivity;
import com.example.fuelqueue.screen.user.FuelStationsListActivity;

import java.util.Objects;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_register);

        Button btnLogin = (Button) findViewById(R.id.btn_register);

        btnLogin.setOnClickListener(view -> {
            Intent intent = new Intent(view.getContext(), OwnerFuelTypeListActivity.class);
            startActivity(intent);
        });
    }
}