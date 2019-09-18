package com.mobileyuone2.penstudiophoto.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.mobileyuone2.penstudiophoto.R;
import com.mobileyuone2.penstudiophoto.model.ResultDataPaketphotosItem;

import java.util.List;

import androidx.annotation.NonNull;

public class AdapterPaket extends ArrayAdapter<ResultDataPaketphotosItem> {

    public AdapterPaket(Context context, List<ResultDataPaketphotosItem> list){
        super(context, 0, list);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView,  ViewGroup parent) {
        return initView(position,convertView,parent);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return initView(position,convertView,parent);
    }

    private View initView(int position, View convertView, ViewGroup parent){
        if(convertView==null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_paket_boking, parent, false);
        }

        TextView idpaketphotobooking = convertView.findViewById(R.id.tv_idpaketphotobooking);
        TextView namapaketphotobooking = convertView.findViewById(R.id.tv_namapaketphotobooking);
        ResultDataPaketphotosItem currentItem = getItem(position);

        if(currentItem != null){
            idpaketphotobooking.setText(currentItem.getIdPaketphoto());
            namapaketphotobooking.setText(currentItem.getNamaPaketphoto());
        }

        return convertView;
    }

}
