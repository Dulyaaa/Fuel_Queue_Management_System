package com.example.fuelqueue;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.fuelqueue.remote.UserService;
import com.example.fuelqueue.model.User;
import com.example.fuelqueue.screen.station_owner.AddNewFuelTypeActivity;
import com.example.fuelqueue.screen.station_owner.UpdateFuelTypeActivity;
import com.example.fuelqueue.screen.user.FuelStationsListActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class LoginActivity extends AppCompatActivity {
    UserService userService;
    List<User> userList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_login);

        Button btnLogin = (Button) findViewById(R.id.btn_log_in);

        btnLogin.setOnClickListener(view -> {
            Intent intent = new Intent(view.getContext(), UpdateFuelTypeActivity.class);
            startActivity(intent);
        });
    }
}