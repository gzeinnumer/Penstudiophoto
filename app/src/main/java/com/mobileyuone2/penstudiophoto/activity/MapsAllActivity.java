package com.mobileyuone2.penstudiophoto.activity;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.mobileyuone2.penstudiophoto.R;
import com.mobileyuone2.penstudiophoto.model.ResponseReadDataStudiophoto;
import com.mobileyuone2.penstudiophoto.model.ResultDataStudiophotosItem;
import com.mobileyuone2.penstudiophoto.network.RetroServer;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MapsAllActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    List<ResultDataStudiophotosItem> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_all);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        RetroServer.getInstance().getDataStudiophoto().enqueue(new Callback<ResponseReadDataStudiophoto>() {
            @Override
            public void onResponse(Call<ResponseReadDataStudiophoto> call, Response<ResponseReadDataStudiophoto> response) {
                if (response.isSuccessful()){
                    if (response.body().getResultDataStudiophotos().size()>0){
                        list=response.body().getResultDataStudiophotos();
                        initMaps();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseReadDataStudiophoto> call, Throwable t) {

            }
        });
    }

    private void initMaps() {

        for (int i=0; i<list.size(); i++){
            // Add a marker in Sydney and move the camera
            LatLng sydney = new LatLng(Double.parseDouble(list.get(i).getLatitude()), Double.parseDouble(list.get(i).getLongitude()));
            mMap.addMarker(new MarkerOptions().position(sydney).title(list.get(i).getNamaStudiophoto()));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        }
    }
}
