package com.mobileyuone2.penstudiophoto.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.mobileyuone2.penstudiophoto.R;
import com.mobileyuone2.penstudiophoto.model.ResponseDataRegister;
import com.mobileyuone2.penstudiophoto.network.RetroServer;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register extends AppCompatActivity {

    TextView reg_judul;
    EditText ed_nama, ed_email, ed_pass, ed_notelp, ed_alamat, ed_confirmpass, ed_jekel;
    ImageView reg_logo;
    Button btnreg;
    Spinner spinnerjekel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        reg_judul = findViewById(R.id.reg_judul);
        ed_nama = findViewById(R.id.ed_nama);
        ed_jekel = findViewById(R.id.ed_jekel);
        ed_email = findViewById(R.id.ed_email);
        ed_pass = findViewById(R.id.ed_pass);
        ed_confirmpass = findViewById(R.id.ed_confirmpass);
        ed_notelp = findViewById(R.id.ed_notelp);
        ed_alamat = findViewById(R.id.ed_alamat);

        reg_logo = findViewById(R.id.reg_logo);

        //spinnerjekel = findViewById(R.id.spinnerjekel);
       // String[] jekel = {"Laki-laki","Perempuan"};

        btnreg = findViewById(R.id.btnreg);

        btnreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchRegister();
            }
        });

        //ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item, jekel);
       // spinnerjekel.setAdapter(adapter);
      //  spinnerjekel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
         //   @Override
           // public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
         //       Toast.makeText(getApplicationContext(), spinnerjekel.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
         //   }

          //  @Override
           // public void onNothingSelected(AdapterView<?> parent) {

        //    }
      //  });

    }

    private void fetchRegister() {
        String nama_pelanggan = ed_nama.getText().toString().trim();
        String jekel = ed_jekel.getText().toString().trim();
        String email = ed_email.getText().toString().trim();
        String password = ed_pass.getText().toString().trim();
        String no_telp = ed_notelp.getText().toString().trim();
        String alamat = ed_alamat.getText().toString().trim();


        if (!nama_pelanggan.equals("") && !jekel.equals("") && !email.equals("") && !password.equals("")  && !no_telp.equals("") && !alamat.equals("")){
            RetroServer.getInstance().register(nama_pelanggan, jekel, email, password, no_telp, alamat).enqueue(new Callback<ResponseDataRegister>() {
                @Override
                public void onResponse(Call<ResponseDataRegister> call, Response<ResponseDataRegister> response) {
                    if (response.isSuccessful()){
                        if (response.body().isStatus()){
                            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                            startActivity(intent);
                            Toast.makeText(Register.this, "Register Berhasil", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(Register.this, "Password Salah", Toast.LENGTH_SHORT).show();
                        }
                    }
                }

                @Override
                public void onFailure(Call<ResponseDataRegister> call, Throwable t) {
                    Toast.makeText(Register.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(this, "Silahkan masukkan password", Toast.LENGTH_SHORT).show();
        }

    }
}
