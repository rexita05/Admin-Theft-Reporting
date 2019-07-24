package com.example.rexita_pc.admin.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rexita_pc.admin.Model.BobotKerugian;
import com.example.rexita_pc.admin.Model.DataPencurian;
import com.example.rexita_pc.admin.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.text.TextUtils.isEmpty;

public class DetailPencurianActivity extends AppCompatActivity {

    @BindView(R.id.txtWaktu) TextView txtWaktu;
    @BindView(R.id.txtKejadian) TextView txtKejadian;
    @BindView(R.id.txtLokasi) TextView txtLokasi;
    @BindView(R.id.txtKecamatan) TextView txtKecamatan;
    @BindView(R.id.txtKerugian) TextView txtKerugian;
    @BindView(R.id.txtTanggal) TextView txtTanggal;
    @BindView(R.id.btnKonfirmasi) Button btnKonfirmasi;
    @BindView(R.id.btnHapus) Button btnHapus;
    DataPencurian dataPencurian;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_pencurian);

        databaseReference = FirebaseDatabase.getInstance().getReference();

        ButterKnife.bind(this);

        dataPencurian = (DataPencurian) getIntent().getSerializableExtra("data");
        if(dataPencurian!=null){
            txtWaktu.setText("Waktu : "+dataPencurian.getWaktu());
            txtTanggal.setText("Tanggal : "+dataPencurian.getTanggal());
            txtKejadian.setText("Kejadian : "+dataPencurian.getKejadian());
            txtLokasi.setText("Lokasi : "+dataPencurian.getLokasi());
            txtKecamatan.setText("Kecamatan : "+dataPencurian.getKecamatan());
            DecimalFormatSymbols symbols = new DecimalFormatSymbols();
            symbols.setDecimalSeparator(',');
            DecimalFormat decimalFormat = new DecimalFormat("Rp ###,###,###,###", symbols);
            String prezzo = decimalFormat.format(Integer.parseInt(dataPencurian.getKerugian()));
            txtKerugian.setText("Kerugian            : "+prezzo);
        }

        btnHapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDeleteData();
            }
        });

        btnKonfirmasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataPencurian.setStatus(1);
                updateKerugian(dataPencurian);

//                if(!isEmpty(txtKerugian.getText().toString()))
//                {
//
//                    dataPencurian.setKerugian(txtKerugian.getText().toString());
//
//                }
//                else{
//                    Snackbar.make(findViewById(R.id.main_layout), "Kolom Kerugian tidak boleh kosong", Snackbar.LENGTH_SHORT).show();
//                }
            }
        });
    }

    private void updateKerugian(DataPencurian dataPencurian) {
        databaseReference.child("data_pencurian").child(dataPencurian.getKey()).setValue(dataPencurian).
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

    public void onDeleteData() {
        databaseReference.child("data_pencurian").child(dataPencurian.getKey()).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                finish();
                Toast.makeText(getApplicationContext(),"Delete Success", Toast.LENGTH_LONG).show();
            }

        });
    }

    public static Intent getActIntent(Activity activity){
        return new Intent(activity, DetailPencurianActivity.class);
    }
}
