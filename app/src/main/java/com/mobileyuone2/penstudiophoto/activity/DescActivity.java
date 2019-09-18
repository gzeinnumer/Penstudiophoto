package com.mobileyuone2.penstudiophoto.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.mobileyuone2.penstudiophoto.R;
import com.mobileyuone2.penstudiophoto.model.ResponseDataJenisPaket;
import com.mobileyuone2.penstudiophoto.model.ResponseReadDataStudiophoto;
import com.mobileyuone2.penstudiophoto.model.ResultDataJenispaketsItem;
import com.mobileyuone2.penstudiophoto.network.RetroServer;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DescActivity extends AppCompatActivity {

    RecyclerView recyclerViewDesc;
    AdapterJenisPaketById adapter;
    String jenisPaket;
    List<ResultDataJenispaketsItem> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desc);
        recyclerViewDesc = findViewById(R.id.rv_desc);
        jenisPaket = getIntent().getStringExtra("data");

        initData();
    }

    private void initData() {
        RetroServer.getInstance().getDataJenisPaket(jenisPaket).enqueue(new Callback<ResponseDataJenisPaket>() {
            @Override
            public void onResponse(Call<ResponseDataJenisPaket> call, Response<ResponseDataJenisPaket> response) {
                list=response.body().getResultDataJenispakets();
                adapter = new AdapterJenisPaketById(getApplicationContext(), list);
                recyclerViewDesc.setAdapter(adapter);
                recyclerViewDesc.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                recyclerViewDesc.setHasFixedSize(true);
            }

            @Override
            public void onFailure(Call<ResponseDataJenisPaket> call, Throwable t) {

            }
        });
    }
}
