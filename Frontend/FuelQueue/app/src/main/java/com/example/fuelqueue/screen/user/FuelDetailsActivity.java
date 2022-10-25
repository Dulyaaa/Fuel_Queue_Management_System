package com.example.fuelqueue.screen.user;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fuelqueue.R;
import com.example.fuelqueue.assets.DataModel;
import com.example.fuelqueue.assets.ItemAdapter;
import com.example.fuelqueue.correct.db.APIUtils;
import com.example.fuelqueue.correct.model.FuelQueue;
import com.example.fuelqueue.correct.model.FuelStock;
import com.example.fuelqueue.correct.remote.FuelQueueService;
import com.example.fuelqueue.correct.remote.FuelStockService;

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
        txtQueueLength.setText(String.valueOf(queue_length));
        txtAvgTime.setText(avg_time);
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
            fuelTxt.add(new DataModel(fuelType1, fuelStockList.get(0).fuelType));
        } else if (item_count == 2) {
            fuelType1.add(fuelStockList.get(0));
            fuelTxt.add(new DataModel(fuelType1, fuelStockList.get(0).fuelType));
            fuelType2.add(fuelStockList.get(1));
            fuelTxt.add(new DataModel(fuelType2, fuelStockList.get(1).fuelType));
        } else if (item_count == 3) {
            fuelType1.add(fuelStockList.get(0));
            fuelTxt.add(new DataModel(fuelType1, fuelStockList.get(0).fuelType));
            fuelType2.add(fuelStockList.get(1));
            fuelTxt.add(new DataModel(fuelType2, fuelStockList.get(1).fuelType));
            fuelType3.add(fuelStockList.get(2));
            fuelTxt.add(new DataModel(fuelType3, fuelStockList.get(2).fuelType));
        } else if (item_count == 4) {
            fuelType1.add(fuelStockList.get(0));
            fuelTxt.add(new DataModel(fuelType1, fuelStockList.get(0).fuelType));
            fuelType2.add(fuelStockList.get(1));
            fuelTxt.add(new DataModel(fuelType2, fuelStockList.get(1).fuelType));
            fuelType3.add(fuelStockList.get(2));
            fuelTxt.add(new DataModel(fuelType3, fuelStockList.get(2).fuelType));
            fuelType4.add(fuelStockList.get(3));
            fuelTxt.add(new DataModel(fuelType4, fuelStockList.get(3).fuelType));
        } else {
            fuelTxt.add(new DataModel(null, "No FuelType To Display"));
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
                    Toast.makeText(FuelDetailsActivity.this, "Joined to the queue successfully!", Toast.LENGTH_LONG).show();
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
                    if (btnType.equals("exit"))
                        btnExit.setEnabled(false);
                    else
                        btnFilled.setEnabled(false);
                }
            }

            /* Check the error when not called backend service */
            @Override
            public void onFailure(@NonNull Call<FuelQueue> call, @NonNull Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }
}


//        List<DataModel> mList = new ArrayList<>();
//
//        //list1
//        List<String> nestedList1 = new ArrayList<>();
//        nestedList1.add("Jams and Honey");
//        nestedList1.add("Pickles and Chutneys");
//
//        List<String> nestedList2 = new ArrayList<>();
//        nestedList2.add("Book");
//        nestedList2.add("Pen");
//        nestedList2.add("Office Chair");
//        nestedList2.add("Pencil");
//
//        List<String> nestedList3 = new ArrayList<>();
//        nestedList3.add("Decorates");
//        nestedList3.add("Tea Table");
//        nestedList3.add("Wall Paint");
//
//        List<String> nestedList4 = new ArrayList<>();
//        nestedList4.add("Pasta");
//        nestedList4.add("Spices");
//        nestedList4.add("Salt");
//
//        mList.add(new DataModel(nestedList1 , "Instant Food and Noodles"));
//        mList.add(new DataModel(nestedList2,"Stationary"));
//        mList.add(new DataModel(nestedList3,"Home Care"));
//        mList.add(new DataModel(nestedList4 ,"Grocery & Staples"));

/* ===============================================*/

//        String fuelName0 = fuelStockList.get(0).fuelType;
//        String fuelStock0 = fuelStockList.get(0).stock;
//        String fuelArrivalTime0 = fuelStockList.get(0).arrivalTime;
//        String fuelFinishTime0 = fuelStockList.get(0).finishTime;
//        fuelType1.add(fuelStock0);
//        fuelType1.add(fuelStockList.get(0));
//        fuelType1.add(String.valueOf(fuelArrivalTime0));
//        fuelType1.add(fuelFinishTime0);

//        String fuelName1 = fuelStockList.get(1).fuelType;
//        String fuelStock1 = fuelStockList.get(1).stock;
//        String fuelArrivalTime1 = fuelStockList.get(1).arrivalTime;
//        String fuelFinishTime1 = fuelStockList.get(1).finishTime;
//        fuelType2.add(fuelStock1);
//        fuelType2.add(fuelStockList.get(1));
//        fuelType2.add(String.valueOf(fuelArrivalTime1));
//        fuelType2.add(fuelFinishTime1);

//        String fuelName2 = fuelStockList.get(0).fuelType;
//        String fuelStock2 = fuelStockList.get(0).stock;
//        String fuelArrivalTime2 = fuelStockList.get(0).arrivalTime;
//        String fuelFinishTime2 = fuelStockList.get(0).finishTime;
//        fuelType3.add(fuelStock2);
//        fuelType3.add(fuelStockList.get(2));
//        fuelType3.add(String.valueOf(fuelArrivalTime2));
//        fuelType3.add(fuelFinishTime2);

//        String fuelName3 = fuelStockList.get(0).fuelType;
//        String fuelStock3 = fuelStockList.get(0).stock;
//        String fuelArrivalTime3 = fuelStockList.get(0).arrivalTime;
//        String fuelFinishTime3 = fuelStockList.get(0).finishTime;
//        fuelType4.add(fuelStock3);
//        fuelType4.add(fuelStockList.get(3));
//        fuelType4.add(String.valueOf(fuelArrivalTime3));
//        fuelType4.add(fuelFinishTime3);

//        fuelTxt.add(new DataModel(fuelType1 , fuelStockList.get(0).fuelType));
//        fuelTxt.add(new DataModel(fuelType2 , fuelStockList.get(1).fuelType));
//        fuelTxt.add(new DataModel(fuelType3 , fuelStockList.get(2).fuelType));
//        fuelTxt.add(new DataModel(fuelType4 , fuelStockList.get(3).fuelType));

/* ===============================================*/
//    public void addUser(User u){
//        Call<User> call = userService.addUser(u);
//        call.enqueue(new Callback<User>() {
//            @Override
//            public void onResponse(Call<User> call, Response<User> response) {
//                if(response.isSuccessful()){
//                    Toast.makeText(UserActivity.this, "User created successfully!", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<User> call, Throwable t) {
//                Log.e("ERROR: ", t.getMessage());
//            }
//        });
//    }


/* ===============================================*/
//        public void updateUser(int id, User u){
//            Call<User> call = userService.updateUser(id, u);
//            call.enqueue(new Callback<User>() {
//                @Override
//                public void onResponse(Call<User> call, Response<User> response) {
//                    if(response.isSuccessful()){
//                        Toast.makeText(UserActivity.this, "User updated successfully!", Toast.LENGTH_SHORT).show();
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<User> call, Throwable t) {
//                    Log.e("ERROR: ", t.getMessage());
//                }
//            });
//        }