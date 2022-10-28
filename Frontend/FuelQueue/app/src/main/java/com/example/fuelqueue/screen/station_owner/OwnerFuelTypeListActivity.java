package com.example.fuelqueue.screen.station_owner;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fuelqueue.R;
import com.example.fuelqueue.db.APIUtils;
import com.example.fuelqueue.model.FuelStation;
import com.example.fuelqueue.model.FuelStock;
import com.example.fuelqueue.remote.FuelStationService;
import com.example.fuelqueue.remote.FuelStockService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Station owner station details
 */
public class OwnerFuelTypeListActivity extends AppCompatActivity {
    /* Fuel stock related variables declaration */
    FuelStockService fuelStockService;
    List<FuelStock> fuelStocks = new ArrayList<>();
    List<FuelStock> fuelStocksByStation = new ArrayList<>();
    String ownerStationId;
    String userId;
    /* Fuel Station related variables declaration */
    List<FuelStation> fuelStationsList = new ArrayList<>();
    FuelStationService fuelStationService;

    /**
     * Initiate the screen with UI
     *
     * @param savedInstanceState Bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_fuel_type_list);
        /* Point the UI in xml */
        CardView card = findViewById(R.id.details_card);
        /* Retrieve details from previous screen */
        setRetrievedText();
        getStations();
        /* Set onClick method for card to update station details */
//        card.setOnClickListener(view -> {
//            Intent intent = new Intent(view.getContext(), UpdateFuelTypeActivity.class);
//            startActivity(intent);
//        });
        /* Get all fuel stock details */
        getFuelStockList();
    }

    /**
     * Retrieve details from previous screen
     */
    public void setRetrievedText() {
        /* Retrieve details from previous screen */
        Bundle extras = getIntent().getExtras();
        userId = extras.getString("user_id");
    }


    /**
     * Retrieve all fuel stock details by station id
     */
    public void getFuelStockList() {
        /* Initiate the fuel station service to get fuel stock list */
        fuelStockService = APIUtils.getFuelStockConnection();
        Call<List<FuelStock>> call = fuelStockService.getAllFuelStock();
        call.enqueue(new Callback<List<FuelStock>>() {
            /* Calling backend service and get response from sever */
            @Override
            public void onResponse(@NonNull Call<List<FuelStock>> call, @NonNull Response<List<FuelStock>> response) {
                if (response.isSuccessful()) {
                    fuelStocks = response.body();
                    assert fuelStocks != null;
                    fuelStockByStationId();
                    // Pass results to ListViewAdapter Class
                    FuelTypeListAdapter listAdapter = new FuelTypeListAdapter(fuelStocksByStation, OwnerFuelTypeListActivity.this);
                    RecyclerView recyclerView = findViewById(R.id.listRecyclerView);
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setLayoutManager(new LinearLayoutManager(OwnerFuelTypeListActivity.this));
                    // Binds the Adapter to the recyclerview
                    recyclerView.setAdapter(listAdapter);
                    Log.e("list: ", String.valueOf(fuelStocksByStation));
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
     * Get all stations
     */
    public void getStations() {
        /* Initiate the fuel station service to get fuel station list */
        fuelStationService = APIUtils.getFuelStationConnection();
        Call<List<FuelStation>> call = fuelStationService.getAllFuelStations();
        call.enqueue(new Callback<List<FuelStation>>() {
            /* Calling backend service and get response from sever */
            @Override
            public void onResponse(@NonNull Call<List<FuelStation>> call, @NonNull Response<List<FuelStation>> response) {
                if (response.isSuccessful()) {
                    fuelStationsList = response.body();
                    if (fuelStationsList != null) {
                        fuelStationByUser();
                    }
                }
            }

            /* Check the error when not called backend service */
            @Override
            public void onFailure(@NonNull Call<List<FuelStation>> call, @NonNull Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    /**
     * Fuel station by user
     */
    public void fuelStationByUser() {
        for (int i = 0; i < fuelStationsList.size(); i++) {
            if (fuelStationsList.get(i).getUserId().equals(userId)) {
                ownerStationId = fuelStationsList.get(i).getId();
            }
        }
    }

    /**
     * Fuel stocks by station id
     */
    public void fuelStockByStationId() {
        for (int i = 0; i < fuelStocks.size(); i++) {
//            if (fuelStocks.get(i).getShedId().equals(ownerStation.getId())) {
            if (fuelStocks.get(i).getShedId().equals(ownerStationId)) {
                fuelStocksByStation.add(fuelStocks.get(i));
            }
        }
    }
}
