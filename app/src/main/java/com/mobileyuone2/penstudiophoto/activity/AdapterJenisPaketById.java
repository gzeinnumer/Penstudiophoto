package com.mobileyuone2.penstudiophoto.activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mobileyuone2.penstudiophoto.R;
import com.mobileyuone2.penstudiophoto.model.ResultDataJenispaketsItem;

import java.util.List;

public class AdapterJenisPaketById extends RecyclerView.Adapter<AdapterJenisPaketById.MyHolder> {
    private Context context;
    private List<ResultDataJenispaketsItem>  list;

    public AdapterJenisPaketById(Context context, List<ResultDataJenispaketsItem> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_jenispake, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        holder.nama.setText(list.get(position).getNamaJenispaket());
        holder.harga.setText(list.get(position).getHarga());
        holder.desc.setText(list.get(position).getDeskripsi());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        TextView nama,harga,desc;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            nama = itemView.findViewById(R.id.nama_jenispaket);
            harga = itemView.findViewById(R.id.harga);
            desc = itemView.findViewById(R.id.deskripsi);
        }
    }
}
