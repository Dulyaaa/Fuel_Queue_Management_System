package com.example.fuelqueue.screen.user;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.fuelqueue.R;
import com.example.fuelqueue.model.FuelStation;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Fuel station list item view adapter
 */
public class FuelStationsListViewAdapter extends BaseAdapter {
    /* Variables declaration */
    private final Context mContext;
    private final LayoutInflater inflater;
    /* Fuel Station related variables declaration */
    private final List<FuelStation> fuelStationsList;
    private final ArrayList<FuelStation> arraylist;
    private final String userId;

    /**
     * Constructor
     *
     * @param context          context
     * @param fuelStationsList FuelStation object
     * @param userId           userId
     */
    public FuelStationsListViewAdapter(Context context, List<FuelStation> fuelStationsList, String userId) {
        this.mContext = context;
        this.fuelStationsList = fuelStationsList;
        this.inflater = LayoutInflater.from(mContext);
        this.arraylist = new ArrayList<>();
        this.arraylist.addAll(fuelStationsList);
        this.userId = userId;
    }

    /**
     * Count items in list
     *
     * @return count
     */
    @Override
    public int getCount() {
        return fuelStationsList.size();
    }

    /**
     * Get items for view
     *
     * @param position position
     * @return FuelStation object
     */
    @Override
    public FuelStation getItem(int position) {
        return fuelStationsList.get(position);
    }

    /**
     * Get item id
     *
     * @param position position
     * @return long
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * Bind the list into view
     *
     * @param position position
     * @param view     view
     * @param parent   parent
     * @return View object
     */
    public View getView(final int position, View view, ViewGroup parent) {
        /* Create view layout */
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.fuel_stations_list_view_items, parent, false);
        /* Locate UI in xml */
        TextView txtStationName = rowView.findViewById(R.id.txtStationName);
        TextView txtStationCity = rowView.findViewById(R.id.txtStationCity);
        TextView txtQueueLength = rowView.findViewById(R.id.length);
        TextView txtAvgTime = rowView.findViewById(R.id.txtAvgTime);
        /* Set the values to UI */
        txtStationName.setText(String.format("%s", fuelStationsList.get(position).getStationName()));
        txtStationCity.setText(String.format("%s", fuelStationsList.get(position).getCity()));
//        txtQueueLength.setText(Integer.parseInt("%d", fuelStationsList.get(position).getQueueLength()));
//        txtQueueLength.setText(String.valueOf(fuelStationsList.get(position).getQueueLength()));
//        txtQueueLength.setText("6");
        txtQueueLength.setText(String.format("%s", fuelStationsList.get(position).getQueueLength()));
        txtAvgTime.setText(String.format("%s", fuelStationsList.get(position).getAvgTime()));
//        txtAvgTime.setText(String.format("%s", fuelStationsList.get(position).getAvgTime()));
        /* Click on card view method */
        rowView.setOnClickListener(v -> {
            /* Pass data to next screen */
            Intent intent = new Intent(mContext, FuelDetailsActivity.class);
            intent.putExtra("station_id", fuelStationsList.get(position).getId());
            intent.putExtra("station_name", fuelStationsList.get(position).getStationName());
            intent.putExtra("station_city", fuelStationsList.get(position).getCity());
            intent.putExtra("queue_length", fuelStationsList.get(position).getQueueLength());
            intent.putExtra("avg_time", fuelStationsList.get(position).getAvgTime());
            intent.putExtra("user_id", userId);
            mContext.startActivity(intent);
        });
        return rowView;
    }

    /**
     * Search fuel stations by city
     *
     * @param charText cityName
     */
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        fuelStationsList.clear();
        if (charText.length() == 0) {
            fuelStationsList.addAll(arraylist);
        } else {
            for (FuelStation fs : arraylist) {
                if (fs.getCity().toLowerCase(Locale.getDefault()).contains(charText)) {
                    fuelStationsList.add(fs);
                }
            }
        }
        notifyDataSetChanged();
    }
}
