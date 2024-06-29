package com.harsh.sharespace.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.harsh.sharespace.R;
import com.harsh.sharespace.adapters.WorkspaceAdapter;
import com.harsh.sharespace.models.WorkspaceModel;

import java.util.ArrayList;

public class WorkspacesFragment extends Fragment {
    ArrayList<WorkspaceModel> alWorkspaces = new ArrayList<>();
    RecyclerView rvWorkspace;
    WorkspaceAdapter adapterWorkspace;
    public WorkspacesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragWorkspaces = inflater.inflate(R.layout.fragment_workspaces, container, false);
        rvWorkspace = fragWorkspaces.findViewById(R.id.rv_workspaces);
        rvWorkspace.setLayoutManager(new LinearLayoutManager(fragWorkspaces.getContext()));
        adapterWorkspace = new WorkspaceAdapter(fragWorkspaces.getContext(), alWorkspaces);

        alWorkspaces.add(new WorkspaceModel("Workspace-1","Chair Table Desk",69000.0, "Gandhinagar", "Harsh Rana"));
        alWorkspaces.add(new WorkspaceModel("Workspace-2","Chair Table Desk",32000.0, "Gandhinagar", "Ruturaj Rathod"));
        alWorkspaces.add(new WorkspaceModel("Workspace-3","Chair Table Desk",85000.0, "Gandhinagar", "Jignesh Rathod"));
        alWorkspaces.add(new WorkspaceModel("Workspace-4","Chair Table Desk",52000.0, "Gandhinagar", "Dhruv Raval"));

        rvWorkspace.setAdapter(adapterWorkspace);
        adapterWorkspace.notifyDataSetChanged();

        return fragWorkspaces;
    }
}