package com.example.rexita_pc.admin.Fragment;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rexita_pc.admin.Model.BobotKerugian;
import com.example.rexita_pc.admin.Model.BobotWaktu;
import com.example.rexita_pc.admin.Model.DataKecamatan;
import com.example.rexita_pc.admin.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.text.TextUtils.isEmpty;

public class BobotFragment extends Fragment {

    @BindView(R.id.txtBobotkerugian) TextView txtBobotkerugian;
    @BindView(R.id.txtBobotwaktu) TextView txtBobotwaktu;
    @BindView(R.id.btnBobotkerugian) Button btnBobotkerugian;
    @BindView(R.id.btnBobotwaktu) Button btnBobotwaktu;

    DatabaseReference databaseReference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        databaseReference = FirebaseDatabase.getInstance().getReference();
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_bobot, container, false);
        ButterKnife.bind(this, rootView);

        btnBobotkerugian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isEmpty(txtBobotkerugian.getText().toString())) {
                    submitBobotkerugian(new BobotKerugian(Float.parseFloat(txtBobotkerugian.getText().toString())));
                } else {
                    Snackbar.make(getView(), "Kolom tidak boleh kosong", Snackbar.LENGTH_LONG).show();
                }
            }
        });

        btnBobotwaktu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isEmpty(txtBobotwaktu.getText().toString())) {
                    submitBobotwaktu(new BobotWaktu(Float.parseFloat(txtBobotwaktu.getText().toString())));
                } else {
                    Snackbar.make(getView(), "Kolom tidak boleh kosong", Snackbar.LENGTH_LONG).show();
                }
            }
        });
        return rootView;
    }

    private void submitBobotkerugian(BobotKerugian dataKerugian) {
        databaseReference.child("data_bobotkerugian").push().setValue(dataKerugian).addOnSuccessListener(getActivity(),
                new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getActivity(), "Bobot berhasil ditambahkan", Toast.LENGTH_LONG).show();
                        txtBobotkerugian.setText("");
                    }
                });
    }

    private void submitBobotwaktu(BobotWaktu dataWaktu) {
        databaseReference.child("data_bobotwaktu").push().setValue(dataWaktu).addOnSuccessListener(getActivity(),
                new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getActivity(), "Bobot berhasil ditambahkan", Toast.LENGTH_LONG).show();
                        txtBobotwaktu.setText("");
                    }
                });
    }
}
