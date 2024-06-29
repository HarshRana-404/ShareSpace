package com.harsh.sharespace.fragments;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.harsh.sharespace.R;

import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddWorkSpaceFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddWorkSpaceFragment extends Fragment {

    ImageView ivAddWorkSpaceCoverImage;
    EditText etName, etState, etCity, etAddress, etPricePerDay;
    Button btnSaveWorkSpace;

    FirebaseFirestore db;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddWorkSpaceFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddWorkSpaceFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddWorkSpaceFragment newInstance(String param1, String param2) {
        AddWorkSpaceFragment fragment = new AddWorkSpaceFragment();
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

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_work_space, container, false);

        ivAddWorkSpaceCoverImage = view.findViewById(R.id.iv_add_workspace_cover_image);
        etName = view.findViewById(R.id.et_name);
        etState = view.findViewById(R.id.et_state);
        etCity = view.findViewById(R.id.et_city);
        etAddress = view.findViewById(R.id.et_address);
        etPricePerDay = view.findViewById(R.id.et_price_per_day);
        btnSaveWorkSpace = view.findViewById(R.id.btn_save_workspace);

        db = FirebaseFirestore.getInstance();

        ivAddWorkSpaceCoverImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_GET_CONTENT);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, 1);
            }
        });

        btnSaveWorkSpace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                storeData();
            }
        });

        return view;
    }

    public void storeData(){
        String name = etName.getText().toString().trim();
        String state = etState.getText().toString().trim();
        String city = etCity.getText().toString().trim();
        String address = etAddress.getText().toString().trim();
        String price = etPricePerDay.getText().toString().trim();

        HashMap<String, String> workspace = new HashMap<>();
        workspace.put("name", name);
        workspace.put("resources", "chair  table  desk");
        workspace.put("priceperday", price);
        workspace.put("address", address);
        workspace.put("owner", FirebaseAuth.getInstance().getCurrentUser().getUid().toString());
        workspace.put("state", state);
        workspace.put("city", city);

        db.collection("workspaces")
                .add(workspace)
                .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(requireContext(), "Work Space added successfully!", Toast.LENGTH_SHORT).show();
                            getActivity().onBackPressed();
                        }
                    }
                });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        try {
            if (resultCode == Activity.RESULT_OK){
                if (requestCode == 1){
                    Uri selectedImgUri = data.getData();

                    if (selectedImgUri != null){
                        ivAddWorkSpaceCoverImage.refreshDrawableState();
                        ivAddWorkSpaceCoverImage.getDrawable().setTintList(null);
                        ivAddWorkSpaceCoverImage.setPadding(0, 0,0,0);
                        ivAddWorkSpaceCoverImage.setImageURI(selectedImgUri);
                    }else {
                        Toast.makeText(requireContext(), "Null Uri returned!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        } catch (Exception e) {
            Toast.makeText(requireContext(), "Edit1: " + e, Toast.LENGTH_SHORT).show();
        }
    }
}