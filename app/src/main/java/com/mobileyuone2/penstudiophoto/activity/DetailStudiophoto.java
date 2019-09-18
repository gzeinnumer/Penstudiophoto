package com.mobileyuone2.penstudiophoto.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.mobileyuone2.penstudiophoto.R;
import com.mobileyuone2.penstudiophoto.adapter.AdpaterPaketphoto;
import com.mobileyuone2.penstudiophoto.model.ResponseReadDataPaketById;
import com.mobileyuone2.penstudiophoto.model.ResultDataPaketphotosItem;
import com.mobileyuone2.penstudiophoto.model.ResultDataStudiophotosItem;
import com.mobileyuone2.penstudiophoto.network.RetroServer;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailStudiophoto extends AppCompatActivity {

    public static final String DATA = "data";
    TextView idstudiophoto, idpemilikstudiophoto, idpaketphoto, idlokasi, namastudiophoto, alamat, latitude, longitude, keterangan;
    Button btnklik;
    ImageView fotostudiophoto;

    RecyclerView recyclerView;

    ResultDataStudiophotosItem resultDataStudiophotosItem;

    AdpaterPaketphoto adapterPaketphoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_studiophoto);

        idstudiophoto = findViewById(R.id.tv_detailidstudio);
        idpemilikstudiophoto = findViewById(R.id.tv_detailidpemilikstudiophoto);
        idpaketphoto = findViewById(R.id.tv_detailidpaketphoto);
        idlokasi = findViewById(R.id.tv_detailidlokasi);
        namastudiophoto = findViewById(R.id.tv_detailnamastudiophoto);
        alamat = findViewById(R.id.tv_detailalamat);
        latitude = findViewById(R.id.tv_detaillatitude);
        longitude = findViewById(R.id.tv_detaillongitude);
        fotostudiophoto = findViewById(R.id.fotostudiophoto);
        keterangan = findViewById(R.id.tv_detailketerangan);
        btnklik = findViewById(R.id.btnklik);

        recyclerView = findViewById(R.id.recylerview);

        resultDataStudiophotosItem = getIntent().getParcelableExtra(DATA);

        idstudiophoto.setText(resultDataStudiophotosItem.getIdStudiophoto());
        idpemilikstudiophoto.setText(resultDataStudiophotosItem.getIdPemilikstudiophoto());
        idpaketphoto.setText(resultDataStudiophotosItem.getIdPaketphoto());
        idlokasi.setText(resultDataStudiophotosItem.getIdLokasi());
        namastudiophoto.setText(resultDataStudiophotosItem.getNamaStudiophoto());
        alamat.setText(resultDataStudiophotosItem.getAlamat());
        latitude.setText(resultDataStudiophotosItem.getLatitude());
        longitude.setText(resultDataStudiophotosItem.getLongitude());
        keterangan.setText(resultDataStudiophotosItem.getKeterangan());

        fetch();

        btnklik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MapsActivity.class);
                intent.putExtra("lat", resultDataStudiophotosItem.getLatitude());
                intent.putExtra("long", resultDataStudiophotosItem.getLongitude());
                startActivity(intent);


            }
        });

        Glide.with(getApplicationContext())
                .load("http://192.168.43.85/WebStudioPhoto/assets/upload/image/"+resultDataStudiophotosItem.getFotoStudiophoto())
                .into(fotostudiophoto);


    }

    private void fetch() {
        Toast.makeText(this, resultDataStudiophotosItem.getIdPaketphoto(), Toast.LENGTH_SHORT).show();
        RetroServer.getInstance().paketphoto(resultDataStudiophotosItem.getIdPaketphoto()).enqueue(new Callback<ResponseReadDataPaketById>() {
            @Override
            public void onResponse(Call<ResponseReadDataPaketById> call, Response<ResponseReadDataPaketById> response) {
                if (response.isSuccessful()){
                    if (response.body().isStatus()){
                        List<ResultDataPaketphotosItem> list = response.body().getResultDataPaketphotos();
                        if (list.size()>0){
                            adapterPaketphoto = new AdpaterPaketphoto(getApplicationContext(),list);
                            recyclerView.setAdapter(adapterPaketphoto);
                            recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                            recyclerView.setHasFixedSize(true);
                            Toast.makeText(DetailStudiophoto.this, String.valueOf(list.size()), Toast.LENGTH_SHORT).show();

                        }else {
                            Toast.makeText(DetailStudiophoto.this, String.valueOf(list.size()), Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseReadDataPaketById> call, Throwable t) {

            }
        });
    }

    public void onBokingClicked(View view) {
        Intent intent = new Intent(getApplicationContext(), Booking.class);
        intent.putExtra("dat_", resultDataStudiophotosItem.getIdPaketphoto());
        startActivity(intent);
    }
}
