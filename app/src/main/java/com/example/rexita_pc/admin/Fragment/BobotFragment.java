package com.example.rexita_pc.admin.Fragment;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rexita_pc.admin.Activity.EditBobotActivity;
import com.example.rexita_pc.admin.Activity.MainActivity;
import com.example.rexita_pc.admin.Model.BobotKerugian;
import com.example.rexita_pc.admin.Model.BobotWaktu;
import com.example.rexita_pc.admin.Model.DataKecamatan;
import com.example.rexita_pc.admin.Model.DataPencurian;
import com.example.rexita_pc.admin.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.text.TextUtils.isEmpty;

public class BobotFragment extends Fragment {

    @BindView(R.id.txtBobotKerugian) TextView txtBobotkerugian;
    @BindView(R.id.txtBobotWaktu) TextView txtBobotwaktu;
//    @BindView(R.id.btnBobotkerugian) Button btnBobotkerugian;
//    @BindView(R.id.btnBobotwaktu) Button btnBobotwaktu;
    @BindView(R.id.btnEdit) FloatingActionButton btnEdit;

    DatabaseReference databaseReference;
    float waktu = 0;
    float kerugian= 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        databaseReference = FirebaseDatabase.getInstance().getReference();
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_bobot, container, false);
        ButterKnife.bind(this, rootView);

        getBobotKerugian();
        getBobotWaktu();

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), EditBobotActivity.class);
                startActivity(intent);
            }
        });

//        btnBobotkerugian.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (!isEmpty(txtBobotkerugian.getText().toString())) {
//                    submitBobotkerugian(new BobotKerugian(Float.parseFloat(txtBobotkerugian.getText().toString())));
//                } else {
//                    Snackbar.make(getView(), "Kolom tidak boleh kosong", Snackbar.LENGTH_LONG).show();
//                }
//            }
//        });
//
//        btnBobotwaktu.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (!isEmpty(txtBobotwaktu.getText().toString())) {
//                    submitBobotwaktu(new BobotWaktu(Float.parseFloat(txtBobotwaktu.getText().toString())));
//                } else {
//                    Snackbar.make(getView(), "Kolom tidak boleh kosong", Snackbar.LENGTH_LONG).show();
//                }
//            }
//        });
        return rootView;
    }

    private void getBobotKerugian() {
        databaseReference.child("data_bobotkerugian").limitToLast(1).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                    kerugian = postSnapshot.child("kerugian").getValue(Float.class);
                    txtBobotkerugian.setText(String.valueOf(kerugian));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Snackbar.make(getView(), "Data Kosong", Snackbar.LENGTH_SHORT).show();
            }
        });
    }

    private void getBobotWaktu() {
        databaseReference.child("data_bobotwaktu").limitToLast(1).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                    waktu = postSnapshot.child("waktu").getValue(Float.class);
                    txtBobotwaktu.setText(String.valueOf(waktu));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Snackbar.make(getView(), "Data Kosong", Snackbar.LENGTH_SHORT).show();
            }
        });
    }

//    private void submitBobotkerugian(BobotKerugian dataKerugian) {
//        databaseReference.child("data_bobotkerugian").push().setValue(dataKerugian).addOnSuccessListener(getActivity(),
//                new OnSuccessListener<Void>() {
//                    @Override
//                    public void onSuccess(Void aVoid) {
//                        Toast.makeText(getActivity(), "Bobot berhasil ditambahkan", Toast.LENGTH_LONG).show();
//                        txtBobotkerugian.setText("");
//                    }
//                });
//    }
//
//    private void submitBobotwaktu(BobotWaktu dataWaktu) {
//        databaseReference.child("data_bobotwaktu").push().setValue(dataWaktu).addOnSuccessListener(getActivity(),
//                new OnSuccessListener<Void>() {
//                    @Override
//                    public void onSuccess(Void aVoid) {
//                        Toast.makeText(getActivity(), "Bobot berhasil ditambahkan", Toast.LENGTH_LONG).show();
//                        txtBobotwaktu.setText("");
//                    }
//                });
//    }
}
