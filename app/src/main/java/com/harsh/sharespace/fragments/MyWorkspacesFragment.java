package com.harsh.sharespace.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.harsh.sharespace.AddNewWorkSpaceActivity;
import com.harsh.sharespace.R;
import com.harsh.sharespace.adapters.WorkspaceAdapter;
import com.harsh.sharespace.models.WorkspaceModel;

import java.util.ArrayList;

public class MyWorkspacesFragment extends Fragment {

    RecyclerView rvMyWorkSpaces;
    ArrayList<WorkspaceModel> alWorkspaces = new ArrayList<>();
    WorkspaceAdapter adapterWorkspace;


    FloatingActionButton fabAddWorkSpace;
    FirebaseFirestore fbStore;
    FirebaseAuth fbAuth;


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

            fbStore = FirebaseFirestore.getInstance();
            fbAuth = FirebaseAuth.getInstance();


            rvMyWorkSpaces.setLayoutManager(new LinearLayoutManager(view.getContext()));
            adapterWorkspace = new WorkspaceAdapter(view.getContext(), alWorkspaces);

            rvMyWorkSpaces.setAdapter(adapterWorkspace);

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

    @Override
    public void onResume() {
        super.onResume();
        alWorkspaces.clear();
        refreshWorkspaces();
    }

    public void refreshWorkspaces(){
        try {
            fbStore.collection("workspaces").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for(QueryDocumentSnapshot doc : task.getResult()){
                        alWorkspaces.add(new WorkspaceModel(doc.getString("name"), doc.getString("resources"), Double.parseDouble(doc.getString("priceperday")), doc.getString("address"), doc.getString("owner")));
                    }
                    adapterWorkspace.notifyDataSetChanged();
                }
            });
        } catch (Exception e) {
            Toast.makeText(requireContext(), e+"", Toast.LENGTH_SHORT).show();
        }
    }

}