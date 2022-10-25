//package com.example.fuelqueue;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ArrayAdapter;
//import android.widget.TextView;
//
//import com.example.fuelqueue.model.FuelStation;
//
//import java.util.List;
//
//
//public class CustomAdapter extends ArrayAdapter<FuelStation> {
//
//    public CustomAdapter(Context context, int resource, List<FuelStation> student) {
//        super(context, resource, student);
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//
//        View v = convertView;
//
//        if (v == null) {
//            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            v = inflater.inflate(R.layout.view_fuel_station, parent, false);
//        }
//
//        FuelStation fuelStation = getItem(position);
//
//        if (fuelStation != null) {
//            TextView tvStudentId = (TextView) v.findViewById(R.id.student_Id);
//            TextView tvStudentName = (TextView) v.findViewById(R.id.student_name);
//            tvStudentId.setText(fuelStation.Id);
//            tvStudentName.setText(fuelStation.stationName);
//        }
//
//        return v;
//    }
//}