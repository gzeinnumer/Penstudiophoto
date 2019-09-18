package com.mobileyuone2.penstudiophoto.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.mobileyuone2.penstudiophoto.DirectionHelper.FetchURL;
import com.mobileyuone2.penstudiophoto.DirectionHelper.TaskLoadedCallback;
import com.mobileyuone2.penstudiophoto.R;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback, TaskLoadedCallback {

    private GoogleMap mMap;
    MarkerOptions place1, place2;

    Location currentLocation;
    FusedLocationProviderClient fusedLocationProviderClient;
    private static final int REQUEST_CODE = 101;

    Polyline currentPolyline;

    Button buttonLoadPolyLine;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        buttonLoadPolyLine = findViewById(R.id.loadPolyLine);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        //SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
              //  .findFragmentById(R.id.map);
       // mapFragment.getMapAsync(this);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        fetchLastLocation();
    }

    private void fetchLastLocation() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                //todo 5
                ActivityCompat.requestPermissions(this, new String[]
                        {Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);
                return;
            }
        }
        // todo 4 make this one
        Task<Location> task = fusedLocationProviderClient.getLastLocation();
        //todo 6
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                //todo 7
                if (location != null){
                    currentLocation = location;
                    Toast.makeText(MapsActivity.this, currentLocation.getLatitude()+","+currentLocation.getLatitude(), Toast.LENGTH_SHORT).show();
                    SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                            .findFragmentById(R.id.map);
                    mapFragment.getMapAsync(MapsActivity.this);
                }
            }
        });
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        String lat = getIntent().getStringExtra("lat");
        String log = getIntent().getStringExtra("long");

        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(Double.parseDouble(lat), Double.parseDouble(log));
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
//
        LatLng latLng = new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());
//        MarkerOptions markerOptions = new MarkerOptions().position(latLng).title("Aku disini");
        googleMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,5));
//        googleMap.addMarker(markerOptions);

        place1 = new MarkerOptions().position(new LatLng(Double.parseDouble(lat),Double.parseDouble(log))).title("Location Tujuan");
        place2 = new MarkerOptions().position(new LatLng(currentLocation.getLatitude(),currentLocation.getLongitude())).title("Location Aku");
//
//        place1 = new MarkerOptions().position(new LatLng(27.658143, 85.3199503)).title("Location 1");
//        place2 = new MarkerOptions().position(new LatLng(27.667491, 85.3208583)).title("Location 2");


        url = getUrl(place1.getPosition(),place2.getPosition(),"driving");

        final String url = getUrl(place1.getPosition(), place2.getPosition(),"driving");
        //todo 17
        new FetchURL(MapsActivity.this).execute(url, "driving");

        mMap.addMarker(place1);
        mMap.addMarker(place2);

        buttonLoadPolyLine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new FetchURL(MapsActivity.this).execute(url, "driving");
            }
        });

    }

    private String getUrl(LatLng position, LatLng position1, String tipe) {
        //awal
        String str_origin = "origin="+position.latitude + "," + position.longitude;
        //akhir
        String str_dest = "destination="+position1.latitude + "," + position1.longitude;
        //mode
        String mode = "mode="+tipe;
        //param to query
        String params = str_origin +"&"+ str_dest +"&"+ mode;
        //out format
        String output = "json";
        //url
        String url = "https://maps.googleapis.com/maps/api/directions/"+output+"?"+params+"&key="+getString(R.string.google_maps_key);
        return url;
    }

    @Override
    public void onTaskDone(Object... values) {
        //todo 20
        if(currentPolyline != null){
            currentPolyline.remove();
        }
        currentPolyline = mMap.addPolyline((PolylineOptions) values[0]);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case REQUEST_CODE:
                if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    fetchLastLocation();
                }
                break;
        }
    }
}

