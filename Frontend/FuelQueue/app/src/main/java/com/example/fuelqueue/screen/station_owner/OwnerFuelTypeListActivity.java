package com.example.fuelqueue.screen.station_owner;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fuelqueue.R;
import com.example.fuelqueue.db.APIUtils;
import com.example.fuelqueue.model.FuelStock;
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
        /* Set onClick method for card to update station details */
        card.setOnClickListener(view -> {
            Intent intent = new Intent(view.getContext(), UpdateFuelTypeActivity.class);
            startActivity(intent);
        });
        /* Get all fuel stock details */
        getFuelStockList();
    }

    /**
     * Retrieve all fuel stock details
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
                    // Pass results to ListViewAdapter Class
                    FuelTypeListAdapter listAdapter = new FuelTypeListAdapter(fuelStocks, OwnerFuelTypeListActivity.this);
                    RecyclerView recyclerView = findViewById(R.id.listRecyclerView);
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setLayoutManager(new LinearLayoutManager(OwnerFuelTypeListActivity.this));
                    // Binds the Adapter to the recyclerview
                    recyclerView.setAdapter(listAdapter);
                    Log.e("list: ", String.valueOf(fuelStocks));
                }
            }

            /* Check the error when not called backend service */
            @Override
            public void onFailure(@NonNull Call<List<FuelStock>> call, @NonNull Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }
}
