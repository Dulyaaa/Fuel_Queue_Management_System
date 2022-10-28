package com.example.fuelqueue;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.fuelqueue.db.APIUtils;
import com.example.fuelqueue.model.User;
import com.example.fuelqueue.remote.UserService;
import com.example.fuelqueue.screen.station_owner.OwnerFuelTypeListActivity;
import com.example.fuelqueue.screen.user.FuelStationsListActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Login screen
 */
public class LoginActivity extends AppCompatActivity {
    /* User related variables declaration */
    User user = new User();
    List<User> userDetailsList = new ArrayList<>();
    UserService userService;
    String userId;
    String role;
    /* Variable related to UI in xml */
    EditText username;
    EditText password;
    Button btnLogin;

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
        identifyAttributes();
        /* Navigate to next screen */
        btnLogin.setOnClickListener(view -> {
            getUserDetails();
            getAllUsers(view);
        });
    }

    /**
     * Identify the xml attributes
     */
    public void identifyAttributes() {
        username = (EditText) findViewById(R.id.login_username);
        password = (EditText) findViewById(R.id.login_password);
        btnLogin = (Button) findViewById(R.id.btn_log_in);
    }

    /**
     * Get user details form UI
     */
    public void getUserDetails() {
        user.setUserName(username.getText().toString());
        user.setPassword(password.getText().toString());
    }

    /**
     * Retrieve all fuel stations
     */
    public void getAllUsers(View view) {
        /* Initiate the fuel station service to get fuel station list */
        userService = APIUtils.getUserConnection();
        Call<List<User>> call = userService.getUsers();
        call.enqueue(new Callback<List<User>>() {
            /* Calling backend service and get response from sever */
            @Override
            public void onResponse(@NonNull Call<List<User>> call, @NonNull Response<List<User>> response) {
                if (response.isSuccessful()) {
                    userDetailsList = response.body();
                    if (userDetailsList != null) {
                        findUser(view);
                    }
                    Log.e("list: ", String.valueOf(userDetailsList));
                }
            }

            /* Check the error when not called backend service */
            @Override
            public void onFailure(@NonNull Call<List<User>> call, @NonNull Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    /**
     * Find the user
     *
     * @param view View
     */
    public void findUser(View view) {
        for (int i = 0; i < userDetailsList.size(); i++) {
            if (userDetailsList.get(i).getUserName().equalsIgnoreCase(user.getUserName()) && userDetailsList.get(i).getPassword().equals(user.getPassword())) {
                userId = userDetailsList.get(i).getId();
                role = userDetailsList.get(i).getRole();
            }
        }
        Intent intent;
        if (role != null) {
            if (role.equals("Station Owner")) {
                intent = new Intent(view.getContext(), OwnerFuelTypeListActivity.class);
            } else {
                intent = new Intent(view.getContext(), FuelStationsListActivity.class);
            }
            Toast.makeText(LoginActivity.this, "Login successfully!", Toast.LENGTH_SHORT).show();
            intent.putExtra("user_id", userId);
            startActivity(intent);
        } else {
            Toast.makeText(LoginActivity.this, "Try again. Login unsuccessfully!", Toast.LENGTH_SHORT).show();
        }
    }
}