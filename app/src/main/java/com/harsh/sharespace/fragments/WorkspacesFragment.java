package com.harsh.sharespace.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.harsh.sharespace.R;
import com.harsh.sharespace.adapters.WorkspaceAdapter;
import com.harsh.sharespace.models.WorkspaceModel;

import java.util.ArrayList;
import java.util.List;

public class WorkspacesFragment extends Fragment {
    ArrayList<WorkspaceModel> alWorkspaces = new ArrayList<>();
    RecyclerView rvWorkspace;
    WorkspaceAdapter adapterWorkspace;
    FirebaseFirestore fbStore;
    FirebaseAuth fbAuth;
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
        try{
            rvWorkspace = fragWorkspaces.findViewById(R.id.rv_workspaces);
            fbStore = FirebaseFirestore.getInstance();
            fbAuth = FirebaseAuth.getInstance();


            rvWorkspace.setLayoutManager(new LinearLayoutManager(fragWorkspaces.getContext()));
            adapterWorkspace = new WorkspaceAdapter(fragWorkspaces.getContext(), alWorkspaces);

            rvWorkspace.setAdapter(adapterWorkspace);
        } catch (Exception e) {
            Toast.makeText(requireContext(), e+"", Toast.LENGTH_SHORT).show();
        }

        return fragWorkspaces;
    }
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