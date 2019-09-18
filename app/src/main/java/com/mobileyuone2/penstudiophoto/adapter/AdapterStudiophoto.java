package com.mobileyuone2.penstudiophoto.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mobileyuone2.penstudiophoto.R;
import com.mobileyuone2.penstudiophoto.activity.DetailStudiophoto;
import com.mobileyuone2.penstudiophoto.model.ResultDataStudiophotosItem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdapterStudiophoto extends RecyclerView.Adapter<AdapterStudiophoto.MyHolder> implements Filterable {

    private Context context;
    private List<ResultDataStudiophotosItem> list;

    private List<ResultDataStudiophotosItem> listFilter;

    public AdapterStudiophoto(Context context, List<ResultDataStudiophotosItem> list) {
        this.context = context;
        this.list = list;

        listFilter = new ArrayList<>(list);
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_studiophoto, viewGroup, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, final int i) {


        myHolder.tv_idstudiophoto.setText(list.get(i).getIdStudiophoto());
        myHolder.tv_idpemilikstudio.setText(list.get(i).getIdPemilikstudiophoto());
        myHolder.tv_idpaketphoto.setText(list.get(i).getIdPaketphoto());
        myHolder.tv_idlokasi.setText(list.get(i).getIdLokasi());
        myHolder.tv_namastudiophoto.setText(list.get(i).getNamaStudiophoto());
        myHolder.tv_alamat.setText(list.get(i).getAlamat());
        myHolder.tv_latitude.setText(list.get(i).getLatitude());
        myHolder.tv_longitude.setText(list.get(i).getLongitude());
        myHolder.tv_keterangan.setText(list.get(i).getKeterangan());
      //  myHolder.img_fotostudiophoto.setText(list.get(i).getFotoStudiophoto());
        myHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailStudiophoto.class);
                intent.putExtra(DetailStudiophoto.DATA, list.get(i));
                context.startActivity(intent);
            }
        });

        Glide.with(context)
                .load("http://192.168.43.85/WebStudioPhoto/assets/upload/image/"+list.get(i).getFotoStudiophoto())
                 .into(myHolder.img_fotostudiophoto);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public Filter getFilter(){
        return exampleFilter;
    }

    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<ResultDataStudiophotosItem> fildteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                Collections.sort(listFilter, new Comparator<ResultDataStudiophotosItem>() {
                    @Override
                    public int compare(ResultDataStudiophotosItem o1, ResultDataStudiophotosItem o2) {
                        return o1.getAlamat().toLowerCase().compareTo(o2.getAlamat().toLowerCase());
                    }
                });
                fildteredList.addAll(listFilter);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (ResultDataStudiophotosItem item : listFilter) {
                    if (item.getAlamat().toLowerCase().contains(filterPattern)) {
                        fildteredList.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = fildteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            list.clear();
            list.addAll((List)results.values);
            notifyDataSetChanged();

        }
    };

    public class MyHolder extends RecyclerView.ViewHolder {
        TextView tv_namastudiophoto, tv_keterangan, tv_alamat, tv_latitude, tv_longitude, tv_idstudiophoto, tv_idpemilikstudio,tv_idpaketphoto, tv_idlokasi  ;
        ImageView img_fotostudiophoto;

        public MyHolder(@NonNull View itemView) {

            super(itemView);
            tv_idstudiophoto = itemView.findViewById(R.id.tv_idstudiophoto);
            tv_idpemilikstudio = itemView.findViewById(R.id.tv_idpemilikstudio);
            tv_idpaketphoto = itemView.findViewById(R.id.tv_idpaketphoto);
            tv_idlokasi = itemView.findViewById(R.id.tv_idlokasi);
            tv_namastudiophoto = itemView.findViewById(R.id.tv_namastudiophoto);
            tv_alamat = itemView.findViewById(R.id.tv_alamat);
            tv_latitude = itemView.findViewById(R.id.tv_latitude);
            tv_longitude = itemView.findViewById(R.id.tv_longitude);
            img_fotostudiophoto = itemView.findViewById(R.id.fotostudiophoto);
            tv_keterangan = itemView.findViewById(R.id.tv_keterangan);


        }


    }
}
