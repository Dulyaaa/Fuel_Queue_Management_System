package com.example.fuelqueue.screen.user;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.SearchView;

import com.example.fuelqueue.R;
import com.example.fuelqueue.correct.db.APIUtils;
import com.example.fuelqueue.correct.model.FuelStation;
import com.example.fuelqueue.correct.remote.FuelStationService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FuelStationsListActivity extends AppCompatActivity implements SearchView.OnQueryTextListener{
    // Declare Variables
//    String[] fuelStationList;
//    ArrayList<FuelStation> arraylist = new ArrayList<>();
    ListView listView;
    FuelStationsListViewAdapter adapter;
    SearchView editSearch;

    FuelStationService fuelStationService;

    List<FuelStation> fuelStationsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        TODO: remove 2 in xml
        setContentView(R.layout.activity_fuel_stations_list2);

        // Generate sample data
//        fuelStationList = new String[]{"Lion", "Tiger", "Dog",
//                "Cat", "Tortoise", "Rat", "Elephant", "Fox",
//                "Cow","Donkey","Monkey"};

        // Locate the ListView in listview_main.xml
        listView = findViewById(R.id.listview);

//        for (String s : fuelStationList) {
//            FuelStation fuelStations = new FuelStation(s);
//            // Binds all strings into an array
//            arraylist.add(fuelStations);
//        }

        // Pass results to ListViewAdapter Class
//        adapter = new FuelStationsListViewAdapter(this, arraylist);

        // Binds the Adapter to the ListView
//        listView.setAdapter(adapter);

        fuelStationService = APIUtils.getFuelStationConnection();

        getFuelStationList();
        // Locate the EditText in listview_main.xml
        editSearch = findViewById(R.id.search);
        editSearch.setOnQueryTextListener(this);
    }

    public void getFuelStationList(){
        Call<List<FuelStation>> call = fuelStationService.getAllFuelStations();
        call.enqueue(new Callback<List<FuelStation>>() {
            @Override
            public void onResponse(@NonNull Call<List<FuelStation>> call, @NonNull Response<List<FuelStation>> response) {
                if(response.isSuccessful()){
                    fuelStationsList = response.body();
                    assert fuelStationsList != null;
                    adapter = new FuelStationsListViewAdapter(FuelStationsListActivity.this, fuelStationsList);
                    listView.setAdapter(adapter);
                    Log.e("list: ", String.valueOf(fuelStationsList));

//                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//                        Map<String, FuelStation> employeesMap = fuelStationsList.stream().collect(Collectors.toMap(FuelStation::getStationName, Function.identity()));
//
//                        System.out.println(employeesMap);
//                    }

                }
            }

            @Override
            public void onFailure(@NonNull Call<List<FuelStation>> call, @NonNull Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        adapter.filter(newText);
        return false;
    }

}