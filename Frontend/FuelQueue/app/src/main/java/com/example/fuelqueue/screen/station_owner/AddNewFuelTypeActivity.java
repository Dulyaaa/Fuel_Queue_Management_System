package com.example.fuelqueue.screen.station_owner;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.fuelqueue.R;
import com.example.fuelqueue.db.APIUtils;
import com.example.fuelqueue.model.FuelStock;
import com.example.fuelqueue.remote.FuelStockService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Add new fuel stock
 */
public class AddNewFuelTypeActivity extends AppCompatActivity {
    /* Fuel Queue related variables declaration */
    FuelStock fuelStock;
    FuelStockService fuelStockService;
    /* UI related variables declaration */
    TextView txtFuelName, txtFuelStock, txtArrivalTime;
    Button btnAdd;

    /**
     * Initiate the screen with UI
     *
     * @param savedInstanceState Bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_fuel_type);
        /* Identified the xml attributes */
        identifyAttributes();
        /* Initiate add fuel stock details method */
        btnAdd.setOnClickListener(v -> {
            fuelStock.setFuelType(String.valueOf(txtFuelName));
            fuelStock.setStock(String.valueOf(txtFuelStock));
            fuelStock.setFuelType(String.valueOf(txtArrivalTime));
            addNewFuelType(fuelStock);
        });
    }

    /**
     * Identify the xml attributes
     */
    public void identifyAttributes() {
        txtFuelName = findViewById(R.id.txtFuelName);
        txtFuelStock = findViewById(R.id.txtFuelStock);
        txtArrivalTime = findViewById(R.id.arrivalTime);
        btnAdd = findViewById(R.id.btnAdd);
    }

    /**
     * Add new fuel stock details
     *
     * @param fuelStock FuelStock
     */
    public void addNewFuelType(FuelStock fuelStock) {
        /* Initiate the fuel queue service for create fuel queue record */
        fuelStockService = APIUtils.getFuelStockConnection();
        Call<FuelStock> call = fuelStockService.addNewFuelStock(fuelStock);
        call.enqueue(new Callback<FuelStock>() {
            /* Calling backend service and get response from sever */
            @Override
            public void onResponse(@NonNull Call<FuelStock> call, @NonNull Response<FuelStock> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(AddNewFuelTypeActivity.this, "Added new fuel stock successfully!", Toast.LENGTH_SHORT).show();
                }
            }

            /* Check the error when not called backend service */
            @Override
            public void onFailure(@NonNull Call<FuelStock> call, @NonNull Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }
}