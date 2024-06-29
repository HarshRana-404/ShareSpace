package com.harsh.sharespace.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.harsh.sharespace.R;

public class WorkspacesFragment extends Fragment {

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
        return fragWorkspaces;
    }
}