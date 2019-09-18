package com.mobileyuone2.penstudiophoto.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.mobileyuone2.penstudiophoto.R;
import com.mobileyuone2.penstudiophoto.model.ResultDataJenispaketsItem;

import java.util.List;

import androidx.annotation.NonNull;

public class AdapterJenisPaket extends ArrayAdapter<ResultDataJenispaketsItem> {

    public AdapterJenisPaket(Context context, List<ResultDataJenispaketsItem> list){

        super(context, 0, list);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return initView(position,convertView,parent);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return initView(position,convertView,parent);
    }

    private View initView(int position, View convertView, ViewGroup parent){
        if(convertView==null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_jenispaket_booking, parent, false);
        }

        TextView idjenispaketbooking = convertView.findViewById(R.id.tv_idjenispaketbooking);
        TextView namajenispaketbooking = convertView.findViewById(R.id.tv_namajenispaketbooking);
        ResultDataJenispaketsItem currentItem = getItem(position);

        if(currentItem != null){
            idjenispaketbooking.setText(currentItem.getIdJenispaket());
            namajenispaketbooking.setText(currentItem.getNamaJenispaket());
        }

        return convertView;
    }

}
