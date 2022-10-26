package com.example.fuelqueue.screen.user;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fuelqueue.R;
import com.example.fuelqueue.db.APIUtils;
import com.example.fuelqueue.model.FuelQueue;
import com.example.fuelqueue.model.FuelStock;
import com.example.fuelqueue.remote.FuelQueueService;
import com.example.fuelqueue.remote.FuelStockService;
import com.example.fuelqueue.screen.user.fuelType.DataModel;
import com.example.fuelqueue.screen.user.fuelType.ItemAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Fuel type details for selected fuel station and join for queue
 */
public class FuelDetailsActivity extends AppCompatActivity {
    /* Fuel Queue related variables declaration */
    FuelQueue fuelQueue = new FuelQueue();
    FuelQueue fuelQueueDetails = new FuelQueue();
    FuelQueueService fuelQueueService;
    /* Fuel Stock related variables declaration */
    FuelStockService fuelStockService;
    List<FuelStock> fuelStockList = new ArrayList<>();
    List<DataModel> fuelTxt = new ArrayList<>();
    List<FuelStock> fuelType1 = new ArrayList<>();
    List<FuelStock> fuelType2 = new ArrayList<>();
    List<FuelStock> fuelType3 = new ArrayList<>();
    List<FuelStock> fuelType4 = new ArrayList<>();
    /* UI related variables declaration */
    TextView txtStationName, txtStationCity, txtQueueLength, txtAvgTime;
    Button btnJoin, btnExit, btnFilled;
    RecyclerView recyclerView;
    /* Other variables declaration */
    String station_id;
    Date currentTime = new Date();

    /**
     * Initiate the screen with UI
     *
     * @param savedInstanceState Bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fuel_details);
        /* Set title and back navigation in title bar */
        setTitle("Fuel & Station Details");
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        /* Identified the xml attributes */
        identifyAttributes();
        /* Retrieve data from previous screen */
        setRetrievedText();
        /* Get fuel types for selected fuel station */
        getFuelTypes();
        /* Initiate join for the queue method */
        btnJoin.setOnClickListener(v -> {
            fuelQueue.setDepartureTime(getCurrentTime());
            fuelQueue.setArrivalTime("");
            fuelQueue.setStatus("InQueue");
            joinToQueue(fuelQueue);
        });
        /* Initiate exit from the queue method */
        btnExit.setOnClickListener(v -> {
            fuelQueue.setArrivalTime(fuelQueueDetails.getArrivalTime());
            fuelQueue.setStatus("Exit");
            updateFuelQueue(Integer.parseInt(fuelQueueDetails.getUserId()), fuelQueue, "exit");
        });
        /* Initiate method for exit from the queue after filling up  */
        btnFilled.setOnClickListener(v -> {
            fuelQueue.setArrivalTime(fuelQueueDetails.getArrivalTime());
            fuelQueue.setDepartureTime(getCurrentTime());
            fuelQueue.setStatus("FilledUp");
            updateFuelQueue(Integer.parseInt(fuelQueueDetails.getUserId()), fuelQueue, "filled");
        });
    }

    /**
     * Identify the xml attributes
     */
    public void identifyAttributes() {
        recyclerView = findViewById(R.id.main_recyclerview);
        txtStationName = findViewById(R.id.txtStationName);
        txtStationCity = findViewById(R.id.txtStationCity);
        txtQueueLength = findViewById(R.id.txtQueueLength);
        txtAvgTime = findViewById(R.id.txtAvgTime);
        btnJoin = findViewById(R.id.join);
        btnExit = findViewById(R.id.exit);
        btnFilled = findViewById(R.id.filled);
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
     * Retrieve fuel types for selected fuel station
     */
    public void getFuelTypes() {
        /* Initiate the fuel stock service for get fuel stock types details */
        fuelStockService = APIUtils.getFuelStockConnection();
        Call<List<FuelStock>> call = fuelStockService.getAllFuelStock();
        call.enqueue(new Callback<List<FuelStock>>() {
            /* Calling backend service and get response from sever */
            @Override
            public void onResponse(@NonNull Call<List<FuelStock>> call, @NonNull Response<List<FuelStock>> response) {
                if (response.isSuccessful()) {
                    fuelStockList = response.body();
                    assert fuelStockList != null;
                    Log.e("list: ", String.valueOf(fuelStockList));
                    /* Extracting fuel stock details for UI */
                    extractText(fuelStockList);
                }
            }

            /* Check the error when not called backend service */
            @Override
            public void onFailure(@NonNull Call<List<FuelStock>> call, @NonNull Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    /**
     * Extracting fuel stock details for UI
     *
     * @param fuelStockList FuelStock array
     */
    public void extractText(List<FuelStock> fuelStockList) {
        /* Get count of fuel stock */
        int item_count = fuelStockList.size();
        /* Extracting fuel stock details for UI */
        if (item_count == 1) {
            fuelType1.add(fuelStockList.get(0));
            fuelTxt.add(new DataModel(fuelType1, fuelStockList.get(0).fuelType, fuelStockList.get(0).status));
        } else if (item_count == 2) {
            fuelType1.add(fuelStockList.get(0));
            fuelTxt.add(new DataModel(fuelType1, fuelStockList.get(0).fuelType, fuelStockList.get(1).status));
            fuelType2.add(fuelStockList.get(1));
            fuelTxt.add(new DataModel(fuelType2, fuelStockList.get(1).fuelType, fuelStockList.get(1).status));
        } else if (item_count == 3) {
            fuelType1.add(fuelStockList.get(0));
            fuelTxt.add(new DataModel(fuelType1, fuelStockList.get(0).fuelType, fuelStockList.get(0).status));
            fuelType2.add(fuelStockList.get(1));
            fuelTxt.add(new DataModel(fuelType2, fuelStockList.get(1).fuelType, fuelStockList.get(1).status));
            fuelType3.add(fuelStockList.get(2));
            fuelTxt.add(new DataModel(fuelType3, fuelStockList.get(2).fuelType, fuelStockList.get(2).status));
        } else if (item_count == 4) {
            fuelType1.add(fuelStockList.get(0));
            fuelTxt.add(new DataModel(fuelType1, fuelStockList.get(0).fuelType, fuelStockList.get(0).status));
            fuelType2.add(fuelStockList.get(1));
            fuelTxt.add(new DataModel(fuelType2, fuelStockList.get(1).fuelType, fuelStockList.get(1).status));
            fuelType3.add(fuelStockList.get(2));
            fuelTxt.add(new DataModel(fuelType3, fuelStockList.get(2).fuelType, fuelStockList.get(2).status));
            fuelType4.add(fuelStockList.get(3));
            fuelTxt.add(new DataModel(fuelType4, fuelStockList.get(3).fuelType, fuelStockList.get(3).status));
        } else {
            fuelTxt.add(new DataModel(null, "No FuelType To Display", ""));
        }
        /* Initiate the RecycleView UI */
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ItemAdapter adapter = new ItemAdapter(fuelTxt);
        recyclerView.setAdapter(adapter);
    }

    /**
     * Get current time in hrs in 12hrs format
     *
     * @return current time
     */
    public String getCurrentTime() {
        currentTime = Calendar.getInstance().getTime();
        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm a");
        return timeFormat.format(currentTime);
    }

    /**
     * Create new record in FuelQueue collection
     *
     * @param fuelQueue FuelQueue object
     */
    public void joinToQueue(FuelQueue fuelQueue) {
        /* Initiate the fuel queue service for create fuel queue record */
        fuelQueueService = APIUtils.getFuelQueueConnection();
        Call<FuelQueue> call = fuelQueueService.addFuelQueue(fuelQueue);
        call.enqueue(new Callback<FuelQueue>() {
            /* Calling backend service and get response from sever */
            @Override
            public void onResponse(@NonNull Call<FuelQueue> call, @NonNull Response<FuelQueue> response) {
                if (response.isSuccessful()) {
                    fuelQueueDetails = response.body();
                    Log.e("fuelQueueDetails: ", String.valueOf(fuelQueueDetails));
                    Toast.makeText(FuelDetailsActivity.this, "Joined to the queue successfully!", Toast.LENGTH_SHORT).show();
                    showAlertDialog(R.layout.dialog_alert_join);
                    /* Disable clickable of join button */
                    btnJoin.setEnabled(false);
                }
            }

            /* Check the error when not called backend service */
            @Override
            public void onFailure(@NonNull Call<FuelQueue> call, @NonNull Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    /**
     * Update the fuel queue record
     *
     * @param id        FuelQueue id
     * @param fuelQueue FuelQueue object
     * @param btnType   btnType
     */
    public void updateFuelQueue(int id, FuelQueue fuelQueue, String btnType) {
        /* Initiate the fuel queue service for create fuel queue record */
        Call<FuelQueue> call = fuelQueueService.updateFuelQueue(id, fuelQueue);
        call.enqueue(new Callback<FuelQueue>() {
            /* Calling backend service and get response from sever */
            @Override
            public void onResponse(@NonNull Call<FuelQueue> call, @NonNull Response<FuelQueue> response) {
                if (response.isSuccessful()) {
                    fuelQueueDetails = response.body();
                    Log.e("fuelQueueDetails: ", String.valueOf(fuelQueueDetails));
                    Toast.makeText(FuelDetailsActivity.this, "Queue details updated successfully!", Toast.LENGTH_LONG).show();
                    /* Disable the clickable of button */
                    if (btnType.equals("exit")) {
                        showAlertDialog(R.layout.dialog_alert_exit);
                        btnExit.setEnabled(false);
                    } else {
                        showAlertDialog(R.layout.dialog_alert_filled);
                        btnFilled.setEnabled(false);
                    }
                }
            }

            /* Check the error when not called backend service */
            @Override
            public void onFailure(@NonNull Call<FuelQueue> call, @NonNull Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    /**
     * Display the alert dialog box
     *
     * @param layout alertdialog
     */
    private void showAlertDialog(int layout) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(FuelDetailsActivity.this);
        View layoutView = getLayoutInflater().inflate(layout, null);
        Button dialogButton = layoutView.findViewById(R.id.btnDialog);
        dialogBuilder.setView(layoutView);
        AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.show();
        dialogButton.setOnClickListener(view -> alertDialog.dismiss());
    }
}
