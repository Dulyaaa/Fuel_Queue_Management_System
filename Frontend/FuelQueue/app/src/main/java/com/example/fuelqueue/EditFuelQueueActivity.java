package com.example.fuelqueue;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fuelqueue.correct.db.APIUtils;
import com.example.fuelqueue.correct.model.FuelQueue;
import com.example.fuelqueue.correct.remote.FuelQueueService;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditFuelQueueActivity extends AppCompatActivity {

    FuelQueueService userService;
    EditText edtUId;
    EditText edtUsername;
    Button btnSave;
    Button btnDel;
    TextView txtUId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_fuel_queue);

        setTitle("Fuel Queue Details");
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        txtUId = findViewById(R.id.txtUId);
        edtUId = findViewById(R.id.edtUId);
        edtUsername = findViewById(R.id.edtUsername);
        btnSave = findViewById(R.id.btnSave);
        btnDel = findViewById(R.id.btnDel);

        userService = APIUtils.getFuelQueueConnection();

        Bundle extras = getIntent().getExtras();
        final String userId = extras.getString("user_id");
        String userName = extras.getString("user_name");

        edtUId.setText(userId);
        edtUsername.setText(userName);

        if(userId != null && userId.trim().length()>0) {
            edtUId.setFocusable(false);
        } else {
            txtUId.setVisibility(View.INVISIBLE);
            edtUId.setVisibility(View.INVISIBLE);
            btnDel.setVisibility(View.INVISIBLE);
        }

        btnSave.setOnClickListener(v -> {
            FuelQueue u = new FuelQueue();
            u.setStatus(edtUsername.getText().toString());
            if(userId != null && userId.trim().length() > 0){
                //update user
                updateUser(Integer.parseInt(userId), u);
            } else {
                //add user
                addUser(u);
            }
        });

        btnDel.setOnClickListener(v -> {
            assert userId != null;
            deleteUser(Integer.parseInt(userId));

            Intent intent = new Intent(EditFuelQueueActivity.this, MainActivity.class);
            startActivity(intent);
        });

    }
    public void addUser(FuelQueue u){
        Call<FuelQueue> call = userService.addFuelQueue(u);
        call.enqueue(new Callback<FuelQueue>() {
            @Override
            public void onResponse(@NonNull Call<FuelQueue> call, @NonNull Response<FuelQueue> response) {
                if(response.isSuccessful()){
                    Toast.makeText(EditFuelQueueActivity.this, "User created successfully!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<FuelQueue> call, @NonNull Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    public void updateUser(int id, FuelQueue u){
        Call<FuelQueue> call = userService.updateFuelQueue(id, u);
        call.enqueue(new Callback<FuelQueue>() {
            @Override
            public void onResponse(@NonNull Call<FuelQueue> call, @NonNull Response<FuelQueue> response) {
                if(response.isSuccessful()){
                    Toast.makeText(EditFuelQueueActivity.this, "User updated successfully!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<FuelQueue> call, @NonNull Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    public void deleteUser(int id){
        Call<FuelQueue> call = userService.deleteFuelQueue(id);
        call.enqueue(new Callback<FuelQueue>() {
            @Override
            public void onResponse(@NonNull Call<FuelQueue> call, @NonNull Response<FuelQueue> response) {
                if(response.isSuccessful()){
                    Toast.makeText(EditFuelQueueActivity.this, "User deleted successfully!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<FuelQueue> call, @NonNull Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}