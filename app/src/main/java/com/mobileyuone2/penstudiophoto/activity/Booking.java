package com.mobileyuone2.penstudiophoto.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.mobileyuone2.penstudiophoto.R;
import com.mobileyuone2.penstudiophoto.adapter.AdapterJenisPaket;
import com.mobileyuone2.penstudiophoto.adapter.AdapterPaket;
import com.mobileyuone2.penstudiophoto.helper.SessionManager;
import com.mobileyuone2.penstudiophoto.model.ResponseDataJenisPaket;
import com.mobileyuone2.penstudiophoto.model.ResponseDataRegister;
import com.mobileyuone2.penstudiophoto.model.ResponseReadDataPaketById;
import com.mobileyuone2.penstudiophoto.model.ResultDataJenispaketsItem;
import com.mobileyuone2.penstudiophoto.model.ResultDataPaketphotosItem;
import com.mobileyuone2.penstudiophoto.network.RetroServer;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Booking extends AppCompatActivity {

    SessionManager sessionManager;
    Spinner spinnersatu, spinnerdua;
    private List<ResultDataPaketphotosItem> mList = new ArrayList<>();
    private List<ResultDataJenispaketsItem> nList = new ArrayList<>();
    private AdapterPaket adapterPaket;
    private AdapterJenisPaket adapterJenisPaket;

    TextView id_pel;
    EditText tglFoto;
    Button btnPesan;

    private String var1, var2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        id_pel = findViewById(R.id.tv_id_user);
        tglFoto = findViewById(R.id.tgl_foto);
        btnPesan = findViewById(R.id.btn_pesan);

        adapterPaket= new AdapterPaket(getApplicationContext(), mList);
        adapterJenisPaket= new AdapterJenisPaket(getApplicationContext(), nList);
        initData();

        sessionManager = new SessionManager(this);
        String idPel = sessionManager.getKEY_IDPELANGGAN();
        String namPel = sessionManager.getKEY_NAMAPELANGGAN();

        id_pel.setText(namPel);

        btnPesan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sentBooking();
            }
        });
    }

    private void sentBooking() {
        SimpleDateFormat time_formatter = new SimpleDateFormat("yyyy-MM-dd");
        String date = time_formatter.format(System.currentTimeMillis());
        String id_studiofoto = getIntent().getStringExtra("dat_");
        String id_pelanggan = sessionManager.getKEY_IDPELANGGAN();
        String id_paketfoto = var1;
        String id_jenispaket = var2;
        String tgl_booking = date;
        String tanggal_foto = tglFoto.getText().toString().trim();

        RetroServer.getInstance().boking(id_studiofoto,id_pelanggan,id_paketfoto,id_jenispaket, tgl_booking, tanggal_foto).enqueue(new Callback<ResponseDataRegister>() {
            @Override
            public void onResponse(Call<ResponseDataRegister> call, Response<ResponseDataRegister> response) {
                Toast.makeText(Booking.this, response.body().getPesan(), Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), DetailStudiophoto.class));
                finish();
            }

            @Override
            public void onFailure(Call<ResponseDataRegister> call, Throwable t) {

            }
        });
    }

    private void initData() {
        String var = getIntent().getStringExtra("data");
//        Toast.makeText(this, var, Toast.LENGTH_SHORT).show();
        RetroServer.getInstance().paketphoto(var).enqueue(new Callback<ResponseReadDataPaketById>() {
            @Override
            public void onResponse(Call<ResponseReadDataPaketById> call, Response<ResponseReadDataPaketById> response) {
                if (response.isSuccessful()){
                    mList = response.body().getResultDataPaketphotos();
                    initSpinner();
                }
            }

            @Override
            public void onFailure(Call<ResponseReadDataPaketById> call, Throwable t) {

            }
        });
    }

    private void initSpinner() {
        spinnersatu = findViewById(R.id.spinnersatu);

        adapterPaket = new AdapterPaket(this, mList);

        spinnersatu.setAdapter(adapterPaket);

        spinnersatu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ResultDataPaketphotosItem currentItem = (ResultDataPaketphotosItem) parent.getItemAtPosition(position);
                var1 = currentItem.getIdPaketphoto();
                initDataJenis(currentItem.getIdJenispaket());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                var1 = mList.get(0).getIdPaketphoto();
                initDataJenis(var1);
            }
        });
    }

    private void initDataJenis(String itemSelected) {
        nList.clear();
        RetroServer.getInstance().getDataJenisPaket(itemSelected).enqueue(new Callback<ResponseDataJenisPaket>() {
            @Override
            public void onResponse(Call<ResponseDataJenisPaket> call, Response<ResponseDataJenisPaket> response) {
                if (response.isSuccessful()){
                    nList=response.body().getResultDataJenispakets();
                    initDataJenisToSpinner();
                }

            }

            @Override
            public void onFailure(Call<ResponseDataJenisPaket> call, Throwable t) {

            }
        });
    }

    private void initDataJenisToSpinner() {

        spinnerdua = findViewById(R.id.spinnerdua);

        adapterJenisPaket = new AdapterJenisPaket(this, nList);

        adapterJenisPaket.notifyDataSetChanged();

        spinnerdua.setAdapter(adapterJenisPaket);

        spinnerdua.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ResultDataJenispaketsItem currentItem = (ResultDataJenispaketsItem) parent.getItemAtPosition(position);
                var2 = currentItem.getIdJenispaket();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                var2 = Booking.this.nList.get(0).getIdJenispaket();
            }
        });
    }

}
