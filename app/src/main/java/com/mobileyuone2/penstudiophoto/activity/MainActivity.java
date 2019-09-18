package com.mobileyuone2.penstudiophoto.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.mobileyuone2.penstudiophoto.R;
import com.mobileyuone2.penstudiophoto.adapter.AdapterStudiophoto;
import com.mobileyuone2.penstudiophoto.helper.SessionManager;
import com.mobileyuone2.penstudiophoto.model.ResponseReadDataStudiophoto;
import com.mobileyuone2.penstudiophoto.model.ResultDataStudiophotosItem;
import com.mobileyuone2.penstudiophoto.network.RetroServer;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView rvStudiophoto;
    SessionManager sessionManager;

    EditText edSearch;

    AdapterStudiophoto adapterStudiophoto;
    List<ResultDataStudiophotosItem> dataList;

    ViewFlipper v_flipper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvStudiophoto = findViewById(R.id.rv_studiophoto);

        edSearch = findViewById(R.id.edSearch);

        sessionManager = new SessionManager(this);

        int images[] = {R.drawable.fotosatu, R.drawable.two, R.drawable.three, R.drawable.four};

        v_flipper = findViewById(R.id.v_flipper);

        for (int image: images){
            flipperImages(image);
        }

        //cek status login
        if (sessionManager.getKEY_EMAIL() == null){
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            finish();
        }

      fetchDataFromServer();


        filterFunction();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.logout) {
            sessionManager.logout();
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            finish();
            return true;
        } else if (id == R.id.about){
            Toast.makeText(this, "ditekan", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }

    public void flipperImages(int image){
        ImageView imageView = new ImageView(this);
        imageView.setBackgroundResource(image);

        v_flipper.addView(imageView);
        v_flipper.setFlipInterval(3000);
        v_flipper.setAutoStart(true);

        v_flipper.setInAnimation(this, android.R.anim.slide_in_left);
        v_flipper.setOutAnimation(this, android.R.anim.slide_out_right);
    }

    private void filterFunction(){

        edSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapterStudiophoto.getFilter().filter(s);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void fetchDataFromServer() {

        Toast.makeText(this, "test", Toast.LENGTH_SHORT).show();
        RetroServer.getInstance().getDataStudiophoto().enqueue(new Callback<ResponseReadDataStudiophoto>() {
            @Override
            public void onResponse(Call<ResponseReadDataStudiophoto> call, Response<ResponseReadDataStudiophoto> response) {
                if (response.isSuccessful()){
                    if (response.body().isStatus()){
                        List<ResultDataStudiophotosItem> list = response.body().getResultDataStudiophotos();
                        if (list.size()>0){

                            adapterStudiophoto = new AdapterStudiophoto(getApplicationContext(), list);
                            adapterStudiophoto.notifyDataSetChanged();

                            rvStudiophoto.setAdapter(adapterStudiophoto);
                           // rvStudiophoto.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                            rvStudiophoto.setLayoutManager(new GridLayoutManager(getApplicationContext(),3));
                            rvStudiophoto.setHasFixedSize(true);
                        }else{
                            Toast.makeText(MainActivity.this, "data tidak ada", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseReadDataStudiophoto> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void onClickMain(View view) {
        startActivity(new Intent(getApplicationContext(), MapsAllActivity.class));
    }
}
