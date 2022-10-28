package com.example.fuelqueue;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.fuelqueue.db.APIUtils;
import com.example.fuelqueue.model.User;
import com.example.fuelqueue.remote.UserService;
import com.example.fuelqueue.screen.station_owner.OwnerFuelTypeListActivity;
import com.example.fuelqueue.screen.user.FuelStationsListActivity;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Initiate the register screen
 */
public class RegisterActivity extends AppCompatActivity {
    /* User related variables declaration */
    User user = new User();
    User userDetails;
    UserService userService;
    /* Variable related to UI in xml */
    EditText username;
    EditText password;
    EditText confirmPassword;
    Button btnRegister;
    RadioGroup radioGroup;
    RadioButton[] selectedRole = new RadioButton[1];

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
        /* Identified the xml attributes */
        identifyAttributes();
        /* Validation and navigate to next screen */
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getUserDetails();
                addNewUser(user, view);
            }
        });
    }

    /**
     * Identify the xml attributes
     */
    public void identifyAttributes() {
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        confirmPassword = (EditText) findViewById(R.id.confirm_password);
        btnRegister = (Button) findViewById(R.id.btn_register);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        selectedRole = new RadioButton[1];
    }

    /**
     * Get user details form UI
     */
    public void getUserDetails() {
        user.setUserName(username.getText().toString());
        user.setPassword(password.getText().toString());
        /* Get radio button text */
        selectedRole[0] = (RadioButton) findViewById(radioGroup.getCheckedRadioButtonId());
        String role = selectedRole[0].getText().toString();
        user.setRole(role);
    }

    /**
     * Check entered passwords similarity
     *
     * @return boolean
     */
    public boolean pwdValidation() {
        return password.equals(confirmPassword);
    }

    /**
     * Create new user
     *
     * @param view context
     */
    public void addNewUser(User user, View view) {
        /* Initiate the fuel queue service for create fuel queue record */
        userService = APIUtils.getUserConnection();
        Call<User> call = userService.addUser(user);
        call.enqueue(new Callback<User>() {
            /* Calling backend service and get response from sever */
            @Override
            public void onResponse(@NonNull Call<User> call, @NonNull Response<User> response) {
                if (response.isSuccessful()) {
                    /* Getting details from backend*/
                    userDetails = response.body();
                    String userId = Objects.requireNonNull(userDetails).getId();
                    String role = Objects.requireNonNull(userDetails).getRole();
                    Toast.makeText(RegisterActivity.this, "Created account successfully!", Toast.LENGTH_SHORT).show();
                    /* Set screen based on type of role */
                    Intent intent;
                    if (role.equals("Station Owner")) {
                        intent = new Intent(view.getContext(), OwnerFuelTypeListActivity.class);
                    } else {
                        intent = new Intent(view.getContext(), FuelStationsListActivity.class);
                    }
                    intent.putExtra("user_id", userId);
                    startActivity(intent);
                }
            }

            /* Check the error when not called backend service */
            @Override
            public void onFailure(@NonNull Call<User> call, @NonNull Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }
}
