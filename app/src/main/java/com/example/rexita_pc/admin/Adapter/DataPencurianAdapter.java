package com.example.rexita_pc.admin.Adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.rexita_pc.admin.Activity.DetailPencurianActivity;
import com.example.rexita_pc.admin.Model.DataPencurian;
import com.example.rexita_pc.admin.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DataPencurianAdapter extends RecyclerView.Adapter<DataPencurianAdapter.ViewHolder> {

    Context context;
    ArrayList<DataPencurian> dataPencurianList;
//    FirebaseDataListener listener;

    public DataPencurianAdapter(Context context, ArrayList<DataPencurian> dataPencurianList){
        this.context = context;
        this.dataPencurianList = dataPencurianList;
    }


    @NonNull
    @Override
    public DataPencurianAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_data_pencurian,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DataPencurianAdapter.ViewHolder holder, final int position) {
        DataPencurian dataPencurian = dataPencurianList.get(position);
        holder.txtKejadian.setText(dataPencurian.getKejadian());
        holder.txtWaktu.setText(dataPencurian.getWaktu());
        holder.txtTanggal.setText(dataPencurian.getTanggal());
        holder.txtKerugian.setText(dataPencurian.getKerugian());
        holder.line.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(DetailPencurianActivity.getActIntent((Activity) context).putExtra("data", dataPencurianList.get(position)));
            }
        });
        int status;
        status = dataPencurian.getStatus();
        if (status == 1){
            holder.status.setText("Sudah dikonfirmasi");
        }
        else {
            holder.status.setText("Belum dikonfirmasi");
        }
    }

    @Override
    public int getItemCount() {
        return dataPencurianList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.line)
        LinearLayout line;
        @BindView(R.id.txtKejadian)
        TextView txtKejadian;
        @BindView(R.id.txtWaktu)
        TextView txtWaktu;
        @BindView(R.id.txtTanggal)
        TextView txtTanggal;
        @BindView(R.id.status)
        TextView status;
        @BindView(R.id.txtKerugian)
        TextView txtKerugian;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

//    public interface FirebaseDataListener{
//        void onDeleteData(Pencurian pencurian, int position);
//    }
//
//    public AdapterPencurianRecyclerView(ArrayList<Pencurian> barangs, Context ctx){
//        /**
//         * Inisiasi data dan variabel yang akan digunakan
//         */
//        daftarPencurian = pencurians;
//        context = ctx;
//        listener = (FirebaseDBReadActivity)ctx;
//    }
}
