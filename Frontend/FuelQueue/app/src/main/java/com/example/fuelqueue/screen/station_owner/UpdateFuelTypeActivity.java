package com.example.fuelqueue.screen.station_owner;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.fuelqueue.R;
import com.example.fuelqueue.db.APIUtils;
import com.example.fuelqueue.model.FuelStock;
import com.example.fuelqueue.remote.FuelStockService;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Update existing fuel stock details
 */
public class UpdateFuelTypeActivity extends AppCompatActivity {
    /* Fuel Stock related variables declaration */
    FuelStock fuelStock = new FuelStock();
    FuelStock fuelStock1 = new FuelStock();
    FuelStockService fuelStockService;
    /* UI related variables declaration */
    TextView txtFuelName, txtFuelStock, txtArrivalTime, txtAvailableStock, dateView;
    Button btnUpdate;

    /**
     * Initiate the screen with UI
     *
     * @param savedInstanceState Bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_fuel_type);
        /* Identified and set to the xml attributes */
        identifyAttributes();
        setRetrievedText();
        /* Initiate add fuel stock details method */
        btnUpdate.setOnClickListener(v -> {
            fuelStock.setFuelType(txtFuelName.getText().toString());
            fuelStock.setStock(txtFuelStock.getText().toString());
            fuelStock.setFuelType(txtArrivalTime.getText().toString());
            int stock = Integer.parseInt(fuelStock.getStock());
            if (stock <= 10) {
                fuelStock.setFinishTime(getCurrentTime());
                fuelStock.setStatus("Not Available");
            }
            updateFuelStock(fuelStock1.getId(), fuelStock, v);
        });
    }

    /**
     * Get current time in hrs in 12hrs format
     *
     * @return current time
     */
    public String getCurrentTime() {
        Date currentTime = Calendar.getInstance().getTime();
        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm a");
        return timeFormat.format(currentTime);
    }

//    /**
//     * Extract date
//     *
//     * @param year  year
//     * @param month
//     * @param day   day
//     */
//    private void showDate(int year, int month, int day) {
//        Calendar calendar = Calendar.getInstance();
//        int year = calendar.get(Calendar.YEAR);
//        dateView.setText(new StringBuilder().append(day).append("/")
//                .append(month).append("/").append(year));
//    }

    /**
     * Identify the xml attributes
     */
    public void identifyAttributes() {
        txtFuelName = findViewById(R.id.fuelType);
        txtFuelStock = findViewById(R.id.txtStock);
        txtArrivalTime = findViewById(R.id.arrival_time);
        txtAvailableStock = findViewById(R.id.txtAvailableStock);
        btnUpdate = findViewById(R.id.btn_update);
    }

    /**
     * Retrieve details from previous screen
     */
    public void setRetrievedText() {
        /* Retrieve details from previous screen */
        Bundle extras = getIntent().getExtras();
        fuelStock1.setId(extras.getString("fuel_id"));
        /* set for the xml attributes */
        txtFuelName.setText(extras.getString("fuel_type"));
        txtFuelStock.setText(extras.getString("stock"));
        txtArrivalTime.setText(extras.getString("arrival"));
        txtAvailableStock.setText(extras.getString("status"));
    }

    /**
     * Update existing fuelStock details
     *
     * @param id        fuelStock id
     * @param fuelStock FuelStock object
     */
    public void updateFuelStock(String id, FuelStock fuelStock, View view) {
        fuelStockService = APIUtils.getFuelStockConnection();
        /* Initiate the fuel queue service for create fuel queue record */
        Call<Void> call = fuelStockService.updateFuelStock(id, fuelStock.getStock(), fuelStock.getArrivalTime(), fuelStock.getFinishTime());
        call.enqueue(new Callback<Void>() {
            /* Calling backend service and get response from sever */
            @Override
            public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(UpdateFuelTypeActivity.this, "Fuel stock updated successfully!", Toast.LENGTH_LONG).show();
                    finish();
                }
            }

            /* Check the error when not called backend service */
            @Override
            public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }
}