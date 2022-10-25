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
import com.example.fuelqueue.correct.model.FuelStock;

import java.util.List;

public class FuelTypeListAdapter extends RecyclerView.Adapter<FuelTypeListAdapter.ViewHolder> {
    private final List<FuelStock> mData;
    private final LayoutInflater mInflater;


    public FuelTypeListAdapter(List<FuelStock> itemList, Context context) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = itemList;
    }

    @Override
    public int getItemCount() { return mData.size(); }


    @NonNull
    @Override
    public FuelTypeListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.fuel_type, null);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(final FuelTypeListAdapter.ViewHolder holder, final int position) {
//        holder.cv.setAnimation(AnimationUtils.loadAnimation(context, R.drawable.fade_transition));
        holder.bindData(mData.get(position));
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iconImage;
        TextView name, city, status;
        CardView cv;

        ViewHolder(View itemView) {
            super(itemView);
            iconImage = itemView.findViewById(R.id.iconImageView);
            name = itemView.findViewById(R.id.nameTextView);
            city = itemView.findViewById(R.id.cityTextView);
            status = itemView.findViewById(R.id.statusTextView);
            cv = itemView.findViewById(R.id.cv);

            cv.setOnClickListener(view -> {
                Intent intent = new Intent(view.getContext(), EditFuelTypeDetailsActivity.class);
                view.getContext().startActivity(intent);
            });
        }


        void bindData(final FuelStock item) {
//            iconImage.setColorFilter(Color.parseColor(item.), PorterDuff.Mode.SRC_IN);
            name.setText(item.getFuelType());
            city.setText(item.getArrivalTime());
            status.setText(item.getFinishTime());
        }

    }
}
