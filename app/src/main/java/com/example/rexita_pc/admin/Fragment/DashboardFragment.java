package com.example.rexita_pc.admin.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rexita_pc.admin.Adapter.DataPencurianAdapter;
import com.example.rexita_pc.admin.Model.DataPencurian;
import com.example.rexita_pc.admin.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DashboardFragment extends Fragment {

    @BindView(R.id.rvData)
    RecyclerView rvData;

    DatabaseReference databaseReference;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<DataPencurian> dataPencurianList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_dashboard, container, false);
        //finding listview
        ButterKnife.bind(this,rootView);
        layoutManager = new LinearLayoutManager(getActivity());
        rvData.setLayoutManager(layoutManager);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        databaseReference.child("data_pencurian").orderByChild("kejadian").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                dataPencurianList = new ArrayList<>();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                    DataPencurian dataPencurian= postSnapshot.getValue(DataPencurian.class);
                    dataPencurian.setKey(postSnapshot.getKey());
                    dataPencurianList.add(dataPencurian);
                }
                adapter = new DataPencurianAdapter(getActivity(), dataPencurianList);
                rvData.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Snackbar.make(getView(), "Data Kosong", Snackbar.LENGTH_SHORT).show();
            }
        });
    }
}
