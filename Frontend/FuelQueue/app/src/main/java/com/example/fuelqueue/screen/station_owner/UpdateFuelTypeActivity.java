package com.example.fuelqueue.screen.station_owner;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.fuelqueue.R;
import com.example.fuelqueue.model.FuelStock;
import com.example.fuelqueue.remote.FuelStockService;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Update existing fuel stock details
 */
public class UpdateFuelTypeActivity extends AppCompatActivity {
    /* Fuel Stock related variables declaration */
    FuelStock fuelStock;
    FuelStockService fuelStockService;
    /* UI related variables declaration */
    TextView txtFuelName, txtFuelStock, txtArrivalTime, dateView;
    Button btnAdd;

    /**
     * Initiate the screen with UI
     *
     * @param savedInstanceState Bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_fuel_type);
        /* Identified the xml attributes */
        identifyAttributes();
        /* Initiate add fuel stock details method */
        btnAdd.setOnClickListener(v -> {
            fuelStock.setFuelType(String.valueOf(txtFuelName));
            fuelStock.setStock(String.valueOf(txtFuelStock));
            fuelStock.setFuelType(String.valueOf(txtArrivalTime));
            updateFuelStock(fuelStock);
        });
    }

    /**
     * Extract date
     *
     * @param year  year
     * @param month
     * @param day   day
     */
    private void showDate(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        dateView.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));
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
     * Retrieve details from previous screen and set for the xml attributes
     */
    public void setRetrievedText() {
        /* Retrieve details from previous screen */
        Bundle extras = getIntent().getExtras();
        station_id = extras.getString("station_id");
        final String station_name = extras.getString("station_name");
        final String station_city = extras.getString("station_city");
        final int queue_length = extras.getInt("queue_length");
        final String avg_time = extras.getString("avg_time");
        /* set for the xml attributes */
        txtStationName.setText(station_name);
        txtStationCity.setText(station_city);
        txtQueueLength.setText("6");
        txtAvgTime.setText("10 min");
        /* Other attributes setting  */
        fuelQueue.setFuelStationId(station_id);
        fuelQueue.setUserId("1234");
    }

    /**
     * Update existing fuelStock details
     *
     * @param id        fuelStock id
     * @param fuelStock FuelStock object
     */
    public void updateFuelStock(int id, FuelStock fuelStock) {
        /* Initiate the fuel queue service for create fuel queue record */
        Call<FuelStock> call = fuelStockService.updateFuelStock(id, fuelStock);
        call.enqueue(new Callback<FuelStock>() {
            /* Calling backend service and get response from sever */
            @Override
            public void onResponse(@NonNull Call<FuelStock> call, @NonNull Response<FuelStock> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(UpdateFuelTypeActivity.this, "Queue stock updated successfully!", Toast.LENGTH_LONG).show();
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