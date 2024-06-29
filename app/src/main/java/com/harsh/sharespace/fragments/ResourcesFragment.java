package com.harsh.sharespace.fragments;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.harsh.sharespace.R;
import com.harsh.sharespace.adapters.ResourceAdapter;
import com.harsh.sharespace.models.ResourceModel;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ResourcesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ResourcesFragment extends Fragment {

    RecyclerView rvResources;
    ArrayList<ResourceModel> alResourceModel;
    ResourceAdapter resourceAdapter;

    FloatingActionButton fabAddResource;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ResourcesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ResourcesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ResourcesFragment newInstance(String param1, String param2) {
        ResourcesFragment fragment = new ResourcesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View viewL = inflater.inflate(R.layout.fragment_resources, container, false);

        rvResources = viewL.findViewById(R.id.rv_resources);
        fabAddResource = viewL.findViewById(R.id.fab_add_resource);

        rvResources.setLayoutManager(new LinearLayoutManager(requireContext()));
        alResourceModel = new ArrayList<>();
        alResourceModel.add(new ResourceModel("234567", "Chair", "4"));
        alResourceModel.add(new ResourceModel("234567", "Table", "6"));
        alResourceModel.add(new ResourceModel("234567", "Desk", "8"));
        resourceAdapter = new ResourceAdapter(requireContext(), alResourceModel);
        rvResources.setAdapter(resourceAdapter);

        fabAddResource.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("MissingInflatedId")
            @Override
            public void onClick(View view) {
                try {
                    View alertView = LayoutInflater.from(requireContext()).inflate(R.layout.add_resource_layout, viewL.findViewById(R.id.ll_root), false);
                    AlertDialog.Builder adb = new AlertDialog.Builder(requireContext());
                    AlertDialog ad;

                    ImageView ivClose = alertView.findViewById(R.id.iv_close);
                    EditText etResourceName = alertView.findViewById(R.id.et_resource_name);
                    EditText etResourceQty = alertView.findViewById(R.id.et_resource_qty);
                    Button btnAddResource = alertView.findViewById(R.id.btn_add_resource);

                    ad = adb.create();
                    ad.setView(alertView);
                    adb.setCancelable(true);

                    ivClose.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            ad.dismiss();
                        }
                    });

                    btnAddResource.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            alResourceModel.add(new ResourceModel("-", etResourceName.getText().toString(), etResourceQty.getText().toString()));
                            resourceAdapter.notifyDataSetChanged();
                            ad.dismiss();
                        }
                    });

                    ad.show();
                } catch (Exception e) {
                    Toast.makeText(requireContext(), e.toString(), Toast.LENGTH_SHORT).show();
                }

            }
        });

        return viewL;
    }
}