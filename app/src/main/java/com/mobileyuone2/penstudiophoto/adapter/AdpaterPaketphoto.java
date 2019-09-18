package com.mobileyuone2.penstudiophoto.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mobileyuone2.penstudiophoto.R;
import com.mobileyuone2.penstudiophoto.model.ResultDataPaketphotosItem;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdpaterPaketphoto extends RecyclerView.Adapter<AdpaterPaketphoto.MyHolder> {

    private Context context;
    private List<ResultDataPaketphotosItem> list;

    public AdpaterPaketphoto(Context context, List<ResultDataPaketphotosItem> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_paket_photo, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {

        holder.tv_id.setText(list.get(position).getId());
        holder.tv_idpaketphoto.setText(list.get(position).getIdPaketphoto());
        holder.tv_idjenispaket.setText(list.get(position).getIdJenispaket());
        holder.tv_namapaketphoto.setText(list.get(position).getNamaPaketphoto());
        holder.tv_tglupdate.setText(list.get(position).getTanggalUpdate());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        TextView tv_id, tv_idpaketphoto, tv_idjenispaket, tv_namapaketphoto, tv_tglupdate;
        public MyHolder(@NonNull View itemView) {

            super(itemView);

            tv_id = itemView.findViewById(R.id.tv_id);
            tv_idpaketphoto = itemView.findViewById(R.id.tv_idpaketphoto);
            tv_idjenispaket = itemView.findViewById(R.id.tv_idjenispaket);
            tv_namapaketphoto = itemView.findViewById(R.id.tv_namapaketphoto);
            tv_tglupdate = itemView.findViewById(R.id.tv_tglupdate);

        }
    }
}
