//package com.example.fuelqueue;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//
//import com.example.fuelqueue.config.ConnectionService;
//import com.example.fuelqueue.config.Service;
//import com.example.fuelqueue.model.FuelStation;
//
//import retrofit2.Callback;
//import retrofit2.Response;
//
//public class AddFuelStationActivity extends AppCompatActivity {
//
//    Button btnSave, btnDelete;
//    Button btnClose;
//    EditText editTextName;
//    EditText editTextEmail;
//    EditText editTextAge;
//    private int _Student_Id=0;
//    Service restService;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        restService = new Service();
//        setContentView(R.layout.activity_add_fuel_station);
//
//        btnSave = (Button) findViewById(R.id.btnSave);
//        btnDelete = (Button) findViewById(R.id.btnDelete);
//        btnClose = (Button) findViewById(R.id.btnClose);
//
//        editTextName = (EditText) findViewById(R.id.editTextName);
//        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
//        editTextAge = (EditText) findViewById(R.id.editTextAge);
//
//        btnSave.setOnClickListener(this);
//        btnDelete.setOnClickListener(this);
//        btnClose.setOnClickListener(this);
//
//        _Student_Id=0;
//        Intent intent = getIntent();
//        _Student_Id = intent.getIntExtra("student_Id", 0);
//        if(_Student_Id>0){
//            restService.FuelStationCreate().getFuelStations(_Student_Id, new Callback<FuelStation>(){
//               @Override
//               public void success(FuelStation fuelStation, Response response){
//
//               }
//            });
//        }
//    }
//
//}