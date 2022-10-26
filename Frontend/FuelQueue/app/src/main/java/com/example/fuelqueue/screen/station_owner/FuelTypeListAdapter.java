package com.example.fuelqueue.screen.station_owner;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fuelqueue.R;
import com.example.fuelqueue.model.FuelStock;

import java.util.List;

/**
 * Fuel station list item view adapter
 *
 * @source
 */
public class FuelTypeListAdapter extends RecyclerView.Adapter<FuelTypeListAdapter.ViewHolder> {
    /* Variables declaration */
    private final List<FuelStock> mData;
    private final LayoutInflater mInflater;

    /**
     * Constructor
     *
     * @param itemList itemList
     * @param context  context
     */
    public FuelTypeListAdapter(List<FuelStock> itemList, Context context) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = itemList;
    }

    /**
     * Count items in list
     *
     * @return count
     */
    @Override
    public int getItemCount() {
        return mData.size();
    }


    /**
     * Bind the list into view
     *
     * @param parent   parent
     * @param viewType view
     * @return View object
     */
    @NonNull
    @Override
    public FuelTypeListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.fuel_type, null);
        return new ViewHolder(view);
    }


    /**
     * Get items for view
     *
     * @param holder   objectHolder
     * @param position * @param position position
     */
    @Override
    public void onBindViewHolder(final FuelTypeListAdapter.ViewHolder holder, final int position) {
        holder.bindData(mData.get(position));
    }


    /**
     * Setting the data for UI in xml
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        /* Variables declaration */
        ImageView iconImage;
        TextView fuelName, stocks, status;
        CardView cardView;

        /**
         * Constructor
         *
         * @param itemView View
         */
        ViewHolder(View itemView) {
            super(itemView);
            iconImage = itemView.findViewById(R.id.iconImageView);
            fuelName = itemView.findViewById(R.id.fuelName);
            stocks = itemView.findViewById(R.id.stocks);
            status = itemView.findViewById(R.id.statusTextView);
            cardView = itemView.findViewById(R.id.cv);
            /* onClick method on cardView */
            cardView.setOnClickListener(view -> {
                Intent intent = new Intent(view.getContext(), UpdateFuelTypeActivity.class);
                view.getContext().startActivity(intent);
            });
        }

        /**
         * Bind the data to UI
         *
         * @param item FuelStock item
         */
        void bindData(final FuelStock item) {
//            iconImage.setColorFilter(Color.parseColor(item.), PorterDuff.Mode.SRC_IN);
            fuelName.setText(item.getFuelType());
            stocks.setText(item.getStock());
            status.setText(item.getStatus());
        }

    }
}
