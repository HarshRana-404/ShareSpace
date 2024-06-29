package com.harsh.sharespace.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.harsh.sharespace.AddNewWorkSpaceActivity;
import com.harsh.sharespace.R;

public class MyWorkspacesFragment extends Fragment {

    RecyclerView rvMyWorkSpaces;

    FloatingActionButton fabAddWorkSpace;

    public MyWorkspacesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_workspaces, container, false);


        try {
            rvMyWorkSpaces = view.findViewById(R.id.rv_my_workspaces);
            fabAddWorkSpace = view.findViewById(R.id.fab_add_workspace);
            rvMyWorkSpaces.setLayoutManager(new LinearLayoutManager(requireContext()));

            fabAddWorkSpace.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(requireContext(), AddNewWorkSpaceActivity.class));
                }
            });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return view;
    }
}