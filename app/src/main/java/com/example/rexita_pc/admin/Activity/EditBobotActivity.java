package com.example.rexita_pc.admin.Activity;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.rexita_pc.admin.Adapter.DataPencurianAdapter;
import com.example.rexita_pc.admin.Model.DataPencurian;
import com.example.rexita_pc.admin.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EditBobotActivity extends AppCompatActivity {

    @BindView(R.id.editKerugian) EditText editKerugian;
    @BindView(R.id.editWaktu) EditText editWaktu;
    @BindView(R.id.btnUpdate) Button btnUpdate;

    DatabaseReference databaseReference;
    float waktu = 0;
    float kerugian= 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_bobot);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        ButterKnife.bind(this);
        getBobotKerugian();
        getBobotWaktu();
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (waktu != 0 && kerugian != 0 && editKerugian.getText().toString() != null && editKerugian.getText().toString() != null){
                    kerugian = Float.parseFloat(editKerugian.getText().toString());
                    waktu = Float.parseFloat(editWaktu.getText().toString());
                    updateKerugian(kerugian);
                    updateWaktu(waktu);
                }
                else {

                }
            }
        });
    }

    private void updateWaktu(float waktu) {
        databaseReference.child("data_bobotwaktu").child("-LhkKQFPMbgclDrWMqD6/waktu").setValue(waktu).
                addOnSuccessListener(this, new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        /**
                         * Baris kode yang akan dipanggil apabila proses update barang sukses
                         */

                        Snackbar.make(findViewById(R.id.main_layout), "Data berhasil di Update", Snackbar.LENGTH_LONG).setAction("Oke", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                finish();
                            }
                        }).show();
                    }
                });
    }

    private void updateKerugian(float kerugian) {
        databaseReference.child("data_bobotkerugian").child("-LhjYpZsM9hzf0HqASHc/kerugian").setValue(kerugian).
                addOnSuccessListener(this, new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        /**
                         * Baris kode yang akan dipanggil apabila proses update barang sukses
                         */

//                        Snackbar.make(findViewById(R.id.main_layout), "Data berhasil di Update", Snackbar.LENGTH_LONG).setAction("Oke", new View.OnClickListener() {
//                            @Override
//                            public void onClick(View view) {
//                                finish();
//                            }
//                        }).show();
                    }
                });
    }

    private void getBobotKerugian() {
        databaseReference.child("data_bobotkerugian").limitToLast(1).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                    kerugian = postSnapshot.child("kerugian").getValue(Float.class);
                    editKerugian.setText(String.valueOf(kerugian));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Snackbar.make(findViewById(R.id.main_layout), "Data Kosong", Snackbar.LENGTH_SHORT).show();
            }
        });
    }
    private void getBobotWaktu() {
        databaseReference.child("data_bobotwaktu").limitToLast(1).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                    waktu = postSnapshot.child("waktu").getValue(Float.class);
                    editWaktu.setText(String.valueOf(waktu));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Snackbar.make(findViewById(R.id.main_layout), "Data Kosong", Snackbar.LENGTH_SHORT).show();
            }
        });
    }
}
