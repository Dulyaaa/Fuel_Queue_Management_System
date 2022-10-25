//package com.example.fuelqueue;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.util.Log;
//import android.widget.Button;
//import android.widget.ListView;
//
//import com.example.fuelqueue.correct.db.APIUtils;
//import com.example.fuelqueue.correct.model.FuelQueue;
//import com.example.fuelqueue.correct.remote.FuelQueueService;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//
//public class FuelQueueActivity extends AppCompatActivity {
//    Button btnAddUser;
//    Button btnGetUsersList;
//    ListView listView;
//
//    FuelQueueService fuelQueueService;
//    List<FuelQueue> list = new ArrayList<>();
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_fuel_queue);
//
//        setTitle("Fuel Queue");
//
//        btnAddUser = findViewById(R.id.btnAddUser);
//        btnGetUsersList = findViewById(R.id.btnGetUsersList);
//        listView = findViewById(R.id.listView);
//        fuelQueueService = APIUtils.getFuelQueueConnection();
//
//        btnGetUsersList.setOnClickListener(v -> getUsersList());
//
//        btnAddUser.setOnClickListener(v -> {
//            Intent intent = new Intent(FuelQueueActivity.this, EditFuelQueueActivity.class);
//            intent.putExtra("user_name", "");
//            startActivity(intent);
//        });
//    }
//
//        public void getUsersList(){
//            Call<List<FuelQueue>> call = fuelQueueService.getAllFuelQueue();
//            call.enqueue(new Callback<List<FuelQueue>>() {
//                @Override
//                public void onResponse(@NonNull Call<List<FuelQueue>> call, @NonNull Response<List<FuelQueue>> response) {
//                    if(response.isSuccessful()){
//                        list = response.body();
//                        assert list != null;
//                        listView.setAdapter(new FuelQueueAdapter(FuelQueueActivity.this, R.layout.list_user, list));
//                        Log.e("list: ", String.valueOf(list));
//                    }
//                }
//
//                @Override
//                public void onFailure(@NonNull Call<List<FuelQueue>> call, @NonNull Throwable t) {
//                    Log.e("ERROR: ", t.getMessage());
//                }
//            });
//        }
//    }