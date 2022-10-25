//package com.example.fuelqueue;
//
//import android.content.Context;
//import android.content.Intent;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ArrayAdapter;
//import android.widget.TextView;
//
//import androidx.annotation.LayoutRes;
//import androidx.annotation.NonNull;
//
//import com.example.fuelqueue.correct.model.FuelQueue;
//import com.example.fuelqueue.screen.user.FuelDetailsActivity;
//
//import java.util.List;
//
//public class FuelQueueAdapter extends ArrayAdapter<FuelQueue> {
//
//    private final Context context;
//    private final List<FuelQueue> fuelQueueList;
//
//    public FuelQueueAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<FuelQueue> objects) {
//        super(context, resource, objects);
//        this.context = context;
//        this.fuelQueueList = objects;
//    }
//
//    @Override
//    public View getView(final int pos, View convertView, ViewGroup parent){
//        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        View rowView = inflater.inflate(R.layout.list_user, parent, false);
//
//        TextView txtUserId = rowView.findViewById(R.id.txtUserId);
//        TextView txtUsername = rowView.findViewById(R.id.txtUsername);
//
//        txtUserId.setText(String.format("#ID: %s", fuelQueueList.get(pos).getId()));
//        txtUsername.setText(String.format("USER NAME: %s", fuelQueueList.get(pos)()));
//
//        rowView.setOnClickListener(v -> {
//            //start Activity User Form
//            Intent intent = new Intent(context, FuelDetailsActivity.class);
//            intent.putExtra("user_id", String.valueOf(fuelQueueList.get(pos).getId()));
//            intent.putExtra("user_name", fuelQueueList.get(pos).getName());
//            context.startActivity(intent);
//        });
//
//        return rowView;
//    }
//}
