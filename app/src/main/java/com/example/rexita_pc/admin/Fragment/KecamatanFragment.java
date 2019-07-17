package com.example.rexita_pc.admin.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rexita_pc.admin.Model.DataKecamatan;
import com.example.rexita_pc.admin.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.text.TextUtils.isEmpty;

public class KecamatanFragment extends Fragment {

    @BindView(R.id.txtKecamatan) TextView txtKecamatan;
    @BindView(R.id.btnSubmit) Button btnSubmit;
    DatabaseReference databaseReference;

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        databaseReference = FirebaseDatabase.getInstance().getReference();
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_kecamatan, container, false);
        ButterKnife.bind(this, rootView);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isEmpty(txtKecamatan.getText().toString())) {
                    submitDataKecamatan(new DataKecamatan(txtKecamatan.getText().toString()));
                } else {
                    Snackbar.make(getView(), "Kolom tidak boleh kosong", Snackbar.LENGTH_LONG).show();
                }
            }
        });
        return rootView;
    }


    private void submitDataKecamatan(DataKecamatan dataKecamatan) {
        databaseReference.child("data_kecamatan").push().setValue(dataKecamatan).addOnSuccessListener(getActivity(),
                new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getActivity(), "Data kecamatan berhasil ditambahkan", Toast.LENGTH_LONG).show();
                        txtKecamatan.setText("");
                    }
                });
    }
}