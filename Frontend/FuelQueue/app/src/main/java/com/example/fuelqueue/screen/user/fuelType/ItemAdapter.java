package com.example.fuelqueue.screen.user.fuelType;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fuelqueue.R;
import com.example.fuelqueue.model.FuelStock;

import java.util.ArrayList;
import java.util.List;

/**
 * Adapter to set the content of fuel stock details for RecycleView
 *
 * @source
 */
public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {
    /* Variable declaration */
    private final List<DataModel> fuelDetails;
    private List<FuelStock> list = new ArrayList<>();

    /* Constructor */
    public ItemAdapter(List<DataModel> list) {
        this.fuelDetails = list;
    }

    /**
     * Create view holder for recyclerView
     *
     * @param parent   ViewGroup
     * @param viewType viewType
     * @return ItemViewHolder with view
     */
    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        /* Get recyclerView item UI */
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.each_item, parent, false);
        return new ItemViewHolder(view);
    }

    /**
     * Bound the viewHolder
     *
     * @param holder   ItemViewHolder with view
     * @param position position
     */
    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        /* Variable declaration */
        DataModel model = fuelDetails.get(position);
        holder.mTextView.setText(model.getItemText());
        holder.fuelStatus.setText(model.getStatus());
        boolean isExpandable = model.isExpandable();
        holder.expandableLayout.setVisibility(isExpandable ? View.VISIBLE : View.GONE);
        /* CardView expandable */
        if (isExpandable) {
            holder.mArrowImage.setImageResource(R.drawable.arrow_up);
        } else {
            holder.mArrowImage.setImageResource(R.drawable.arrow_down);
        }
        /* Initiate nested recycleView item */
        NestedAdapter adapter = new NestedAdapter(list);
        holder.nestedRecyclerView.setLayoutManager(new LinearLayoutManager(holder.itemView.getContext()));
        holder.nestedRecyclerView.setHasFixedSize(true);
        holder.nestedRecyclerView.setAdapter(adapter);
        holder.linearLayout.setOnClickListener(v -> {
            model.setExpandable(!model.isExpandable());
            list = model.getNestedList();
            notifyItemChanged(holder.getAdapterPosition());
        });
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
    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        /* Variable declaration */
        private final LinearLayout linearLayout;
        private final RelativeLayout expandableLayout;
        private final TextView mTextView;
        private final TextView fuelStatus;
        private final ImageView mArrowImage;
        private final RecyclerView nestedRecyclerView;

        /* Constructor */
        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            linearLayout = itemView.findViewById(R.id.linear_layout);
            expandableLayout = itemView.findViewById(R.id.expandable_layout);
            mTextView = itemView.findViewById(R.id.itemTv);
            fuelStatus = itemView.findViewById(R.id.fuelStatus);
            mArrowImage = itemView.findViewById(R.id.arro_imageview);
            nestedRecyclerView = itemView.findViewById(R.id.child_rv);
        }
    }
}
