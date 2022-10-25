package com.example.fuelqueue.screen.user;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.fuelqueue.R;
import com.example.fuelqueue.correct.model.FuelStation;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class FuelStationsListViewAdapter extends BaseAdapter {

    // Declare Variables
    private final Context mContext;
    private final LayoutInflater inflater;
    private final List<FuelStation> fuelStationsList;
    private final ArrayList<FuelStation> arraylist;

    public FuelStationsListViewAdapter(Context context, List<FuelStation> fuelStationsList){
        this.mContext = context;
        this.fuelStationsList = fuelStationsList;
        this.inflater = LayoutInflater.from(mContext);
        this.arraylist = new ArrayList<>();
        this.arraylist.addAll(fuelStationsList);
    }

    public static class ViewHolder {
        TextView name;
    }

    @Override
    public int getCount() {
        return fuelStationsList.size();
    }

    @Override
    public FuelStation getItem(int position) {
        return fuelStationsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View view, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.fuel_stations_list_view_items, parent, false);

//        TextView txtUsername = rowView.findViewById(R.id.name);

        TextView txtStationName = rowView.findViewById(R.id.txtStationName);
        TextView txtStationCity = rowView.findViewById(R.id.txtStationCity);
        TextView txtQueueLength = rowView.findViewById(R.id.txtQueueLength);
        TextView txtAvgTime = rowView.findViewById(R.id.txtAvgTime);

        txtStationName.setText(String.format("%s", fuelStationsList.get(position).getStationName()));
        txtStationCity.setText(String.format("%s", fuelStationsList.get(position).getCity()));
//        txtQueueLength.setText(Integer.parseInt("%d", fuelStationsList.get(position).getQueueLength()));
        txtQueueLength.setText(String.valueOf( fuelStationsList.get(position).getQueueLength()));
        txtAvgTime.setText(String.format("%s", fuelStationsList.get(position).getAvgTime()));

//        txtUserId.setText(String.format("#ID: %s", fuelQueueList.get(pos).getId()));
//        txtUsername.setText(String.format("USER NAME: %s", fuelQueueList.get(pos).getName()));


//        txtUsername.setText(String.format("USER NAME: %s", fuelStationsList.get(position).getStationName()));

//        final ViewHolder holder;
//        if (view == null) {
//            holder = new ViewHolder();
//            view = inflater.inflate(R.layout.fuel_stations_list_view_items, null);
//            // Locate the TextViews in listview_item.xml
//            holder.name = view.findViewById(R.id.name);
//            view.setTag(holder);
//        } else {
//            holder = (ViewHolder) view.getTag();
//        }
//        // Set the results into TextViews
//        holder.name.setText(fuelStationsList.get(position).getStationName());


        rowView.setOnClickListener(v -> {
            //start Activity User Form
            Intent intent = new Intent(mContext, FuelDetailsActivity.class);
            intent.putExtra("station_id", fuelStationsList.get(position).getId());
            intent.putExtra("station_name", fuelStationsList.get(position).getStationName());
            intent.putExtra("station_city", fuelStationsList.get(position).getCity());
            intent.putExtra("queue_length", fuelStationsList.get(position).getQueueLength());
            intent.putExtra("avg_time", fuelStationsList.get(position).getAvgTime());
            mContext.startActivity(intent);
        });

        return rowView;
    }

    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        fuelStationsList.clear();
        if(charText.length() == 0) {
            fuelStationsList.addAll(arraylist);
        }else {
            for(FuelStation fs:arraylist) {
                if(fs.getStationName().toLowerCase(Locale.getDefault()).contains(charText)) {
                    fuelStationsList.add(fs);
                }
            }
        }
        notifyDataSetChanged();
    }
}
