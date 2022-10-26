package com.example.fuelqueue.screen.user.fuelType;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fuelqueue.R;
import com.example.fuelqueue.model.FuelStock;

import java.util.List;

/**
 * Adapter to set the content of fuel stock details for Nested RecycleView
 *
 * @source
 */
public class NestedAdapter extends RecyclerView.Adapter<NestedAdapter.NestedViewHolder> {
    /* Variable declaration */
    private final List<FuelStock> fuelDetails;

    /* Constructor */
    public NestedAdapter(List<FuelStock> list) {
        this.fuelDetails = list;
    }

    /**
     * Create view holder for recyclerView
     *
     * @param parent   ViewGroup
     * @param viewType viewType
     * @return NestedViewHolder with view
     */
    @NonNull
    @Override
    public NestedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.nested_item, parent, false);
        return new NestedViewHolder(view);
    }

    /**
     * Bound the nestedViewHolder
     *
     * @param holder   NestedViewHolder
     * @param position position
     */
    @Override
    public void onBindViewHolder(@NonNull NestedViewHolder holder, int position) {
//        holder.mTv.setText(mList.get(position).arrivalTime);
//        holder.txtAvailableStock.setText(mList.get(position).arrivalTime);
        holder.txtArrivalTime.setText(fuelDetails.get(position).arrivalTime);
        holder.txtFinishTime.setText(fuelDetails.get(position).finishTime);
    }

    /**
     * Count items in list
     *
     * @return count
     */
    @Override
    public int getItemCount() {
        return fuelDetails.size();
    }

    /**
     * Bind the details for UI
     */
    public static class NestedViewHolder extends RecyclerView.ViewHolder {
        //        private final TextView mTv;
        private final TextView txtAvailableStock;
        private final TextView txtArrivalTime;
        private final TextView txtFinishTime;

        /* Constructor */
        public NestedViewHolder(@NonNull View itemView) {
            super(itemView);
//            mTv = itemView.findViewById(R.id.nestedItemTv);
            txtAvailableStock = itemView.findViewById(R.id.txtAvailableStock);
            txtArrivalTime = itemView.findViewById(R.id.txtArrivalTime);
            txtFinishTime = itemView.findViewById(R.id.txtFinishTime);
        }
    }
}