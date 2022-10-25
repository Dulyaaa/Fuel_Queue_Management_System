//package com.example.fuelqueue;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.ListView;
//import android.widget.Toast;
//
//import com.example.fuelqueue.config.Service;
//import com.example.fuelqueue.model.FuelStation;
//
//import java.util.List;
//
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//
//public class FuelStationListActivity extends AppCompatActivity {
//    Button btnGetAll,btnAdd;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_fuel_stations_list);
//        btnGetAll = (Button) findViewById(R.id.btnGetAll);
//        btnGetAll.setOnClickListener((View.OnClickListener) this);
//
//        btnAdd= (Button) findViewById(R.id.btnAdd);
//        btnAdd.setOnClickListener((View.OnClickListener) this);
//    }
//
////    @Override
////    public boolean onCreateOptionsMenu(Menu menu){
////        getMenuInflater().inflate(R.menu.menu_main, menu);
////        return true;
////    }
//
//    @Override
//    public void onResume() {
//        super.onResume();
//        refreshScreen();
//    }
//
//    private void refreshScreen() {
//        Service.FuelStationCreate().getFuelStations(new Callback<List<FuelStation>>() {
//            @Override
//            public void onResponse(@NonNull Call<List<FuelStation>> call, @NonNull Response<List<FuelStation>> response) {
//                ListView listView = (ListView)  findViewById(R.id.listView);
//
//                CustomAdapter customAdapter = new CustomAdapter(FuelStationListActivity.this, R.layout.view_fuel_station, (List<FuelStation>) call);
//
////                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
////                    @Override
////                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
////                        student_Id = (TextView) view.findViewById(R.id.student_Id);
////                        String studentId = student_Id.getText().toString();
////                        Intent objIndent = new Intent(getApplicationContext(), StudentDetail.class);
////                        objIndent.putExtra("student_Id", Integer.parseInt(studentId));
////                        startActivity(objIndent);
////                    }
////                });
//
//                listView.setAdapter(customAdapter);
//            }
//
//            @Override
//            public void onFailure(@NonNull Call<List<FuelStation>> call, @NonNull Throwable t) {
//                Toast.makeText(FuelStationListActivity.this, "error", Toast.LENGTH_LONG).show();
//            }
//
//        });
//    }
//}
