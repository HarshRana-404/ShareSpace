package com.harsh.sharespace.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.harsh.sharespace.BookWorkspaceActivity;
import com.harsh.sharespace.R;
import com.harsh.sharespace.models.WorkspaceModel;

import java.util.ArrayList;

public class WorkspaceAdapter extends RecyclerView.Adapter<WorkspaceAdapter.WorkspaceViewHolder> {

    ArrayList<WorkspaceModel> alWorkspaces;
    Context context;

    public WorkspaceAdapter(Context context, ArrayList<WorkspaceModel> alWorkspaces){
        this.alWorkspaces = alWorkspaces;
        this.context = context;
    }
    @NonNull
    @Override
    public WorkspaceAdapter.WorkspaceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewWorkSpace = LayoutInflater.from(context).inflate(R.layout.workspace_ui, parent, false);
        return new WorkspaceViewHolder(viewWorkSpace);
    }
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull WorkspaceAdapter.WorkspaceViewHolder holder, int position) {
        try{
            holder.tvWorkspaceName.setText(alWorkspaces.get(position).getName());
            holder.tvWorkspaceResources.setText(alWorkspaces.get(position).getResources());
            holder.tvWorkspaceAddress.setText(alWorkspaces.get(position).getAddress());
            holder.tvWorkspacePricePerDay.setText(alWorkspaces.get(position).getPricePerDay().toString());

            holder.llWorkspace.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context.startActivity(new Intent(context, BookWorkspaceActivity.class));
                }
            });

        } catch (Exception e) {

        }
    }

    @Override
    public int getItemCount() {
        return alWorkspaces.size();
    }

    public class WorkspaceViewHolder extends RecyclerView.ViewHolder {
        ImageView ivWorkspaceCoverImage;
        TextView tvWorkspaceName, tvWorkspaceResources, tvWorkspaceAddress, tvWorkspacePricePerDay;
        LinearLayout llWorkspace;
        public WorkspaceViewHolder(@NonNull View itemView) {
            super(itemView);
            try{
                llWorkspace = itemView.findViewById(R.id.ll_workspace);
                ivWorkspaceCoverImage = itemView.findViewById(R.id.iv_workspace_cover_image);
                tvWorkspaceName = itemView.findViewById(R.id.tv_workspace_name);
                tvWorkspaceResources = itemView.findViewById(R.id.tv_workspace_resources);
                tvWorkspaceAddress = itemView.findViewById(R.id.tv_workspace_address);
                tvWorkspacePricePerDay = itemView.findViewById(R.id.tv_workspace_price_per_day);
            } catch (Exception e) {
                Toast.makeText(context, e+"", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
