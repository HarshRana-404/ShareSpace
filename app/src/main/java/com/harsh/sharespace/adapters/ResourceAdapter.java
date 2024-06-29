package com.harsh.sharespace.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.harsh.sharespace.R;
import com.harsh.sharespace.models.ResourceModel;

import java.util.ArrayList;

public class ResourceAdapter extends RecyclerView.Adapter<ResourceAdapter.ViewHolder> {

    Context context;
    ArrayList<ResourceModel> alResourceModel;

    public ResourceAdapter(Context context, ArrayList<ResourceModel> alResourceModel) {
        this.context = context;
        this.alResourceModel = alResourceModel;
    }

    @NonNull
    @Override
    public ResourceAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.resource_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ResourceAdapter.ViewHolder holder, int position) {
        holder.tvResourceName.setText(alResourceModel.get(position).name);
        holder.tvResourceQty.setText(alResourceModel.get(position).quantity);

        holder.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alResourceModel.remove(position);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return alResourceModel.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvResourceName, tvResourceQty;
        ImageView ivDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvResourceName = itemView.findViewById(R.id.tv_resource_name);
            tvResourceQty = itemView.findViewById(R.id.tv_resource_qty);
            ivDelete = itemView.findViewById(R.id.iv_delete);
        }
    }
}
