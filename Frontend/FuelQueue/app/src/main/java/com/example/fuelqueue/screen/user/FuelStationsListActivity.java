package com.example.fuelqueue.screen.user;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.fuelqueue.R;
import com.example.fuelqueue.db.APIUtils;
import com.example.fuelqueue.model.FuelStation;
import com.example.fuelqueue.remote.FuelStationService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Fuel station list for user
 */
public class FuelStationsListActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
    /* Fuel Station related variables declaration */
    List<FuelStation> fuelStationsList = new ArrayList<>();
    FuelStationService fuelStationService;
    /* UI related variables declaration */
    FuelStationsListViewAdapter adapter;
    ListView listView;
    SearchView editSearch;

    /**
     * Initiate the screen with UI
     *
     * @param savedInstanceState Bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        TODO: remove 2 in xml
        setContentView(R.layout.activity_fuel_stations_list2);

        /* Point the UI in xml */
        editSearch = findViewById(R.id.search);
        listView = findViewById(R.id.listview);
        /* Get all fuel stations */
        getFuelStationList();
        /* Search the duel stations by city */
        editSearch.setOnQueryTextListener(this);
    }

    /**
     * Retrieve all fuel stations
     */
    public void getFuelStationList() {
        /* Initiate the fuel station service to get fuel station list */
        fuelStationService = APIUtils.getFuelStationConnection();
        Call<List<FuelStation>> call = fuelStationService.getAllFuelStations();
        call.enqueue(new Callback<List<FuelStation>>() {
            /* Calling backend service and get response from sever */
            @Override
            public void onResponse(@NonNull Call<List<FuelStation>> call, @NonNull Response<List<FuelStation>> response) {
                if (response.isSuccessful()) {
                    fuelStationsList = response.body();
                    assert fuelStationsList != null;
                    // Pass results to ListViewAdapter Class
                    adapter = new FuelStationsListViewAdapter(FuelStationsListActivity.this, fuelStationsList);
                    // Binds the Adapter to the ListView
                    listView.setAdapter(adapter);
                    Log.e("list: ", String.valueOf(fuelStationsList));
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
     * Submits the query
     *
     * @param query String cityName
     * @return boolean
     */
    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    /**
     * Filter the fuel stations by city
     *
     * @param newText String cityName
     * @return boolean
     */
    @Override
    public boolean onQueryTextChange(String newText) {
        adapter.filter(newText);
        return false;
    }
}
