package com.example.fuelqueue.screen.station_owner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.example.fuelqueue.R;
import com.example.fuelqueue.correct.db.APIUtils;
import com.example.fuelqueue.correct.model.FuelStock;
import com.example.fuelqueue.correct.remote.FuelStockService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OwnerFuelTypeListActivity extends AppCompatActivity {
//    List<ListElement> elements;
    ListView listView;
    FuelTypeListAdapter adapter;
    FuelStockService fuelStockService;
    List<FuelStock> fuelStocks = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_fuel_type_list);

        CardView card = findViewById(R.id.details_card);

        card.setOnClickListener(view -> {
            Intent intent = new Intent(view.getContext(), EditFuelTypeDetailsActivity.class);
            startActivity(intent);
        });

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

//        init();

        init1();
    }

//    public void populateList() {
//        elements = new ArrayList<>();
//        elements.add(new ListElement("#775447", "Peter", "Vancouver", "Active"));
//        elements.add(new ListElement("#607d8b", "Alejandra", "Mexico City", "Active"));
//        elements.add(new ListElement("#03a9f4", "Martin", "Ontario", "Canceled"));
//        elements.add(new ListElement("#f44336", "Rachel", "New York", "Inactive"));
//    }

//    public void init() {
//        populateList();
//
//        FuelTypeListAdapter listAdapter = new FuelTypeListAdapter(elements, this);
//        RecyclerView recyclerView = findViewById(R.id.listRecyclerView);
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setAdapter(listAdapter);
//    }

    public void init1(){
//        listView = findViewById(R.id.listRecyclerView);
        fuelStockService = APIUtils.getFuelStockConnection();

        getFuelStockList();
    }

    public void getFuelStockList(){
        Call<List<FuelStock>> call = fuelStockService.getAllFuelStock();
        call.enqueue(new Callback<List<FuelStock>>() {
            @Override
            public void onResponse(@NonNull Call<List<FuelStock>> call, @NonNull Response<List<FuelStock>> response) {
                if(response.isSuccessful()){
                    fuelStocks = response.body();
                    assert fuelStocks != null;
                    FuelTypeListAdapter listAdapter = new FuelTypeListAdapter(fuelStocks, OwnerFuelTypeListActivity.this);
                    RecyclerView recyclerView = findViewById(R.id.listRecyclerView);
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setLayoutManager(new LinearLayoutManager(OwnerFuelTypeListActivity.this));
                    recyclerView.setAdapter(listAdapter);
                    Log.e("list: ", String.valueOf(fuelStocks));
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<FuelStock>> call, @NonNull Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }
}