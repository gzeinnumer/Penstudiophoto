package com.mobileyuone2.penstudiophoto.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mobileyuone2.penstudiophoto.R;
import com.mobileyuone2.penstudiophoto.helper.SessionManager;
import com.mobileyuone2.penstudiophoto.model.ResponseLogin;
import com.mobileyuone2.penstudiophoto.network.RetroServer;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//todo 1

public class LoginActivity extends AppCompatActivity {

    TextView judullogin;
    EditText edemail, edpassword;
    Button btnlogin, btnregister;
    ImageView log_image;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        judullogin = findViewById(R.id.judullogin);

        edemail = findViewById(R.id.email);
        edpassword = findViewById(R.id.password);
        btnlogin = findViewById(R.id.btnlogin);
        btnregister = findViewById(R.id.btnregister);

        log_image = findViewById(R.id.log_image);

        sessionManager = new SessionManager(this);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchLogin();
            }
        });

        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,Register.class);
                startActivity(intent);
                Toast.makeText(LoginActivity.this, "Login Berhasil", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void fetchLogin() {
        String email = edemail.getText().toString().trim();
        String password = edpassword.getText().toString().trim();

        if (!email.equals("") && !password.equals("")){
            RetroServer.getInstance().login(email, password).enqueue(new Callback<ResponseLogin>() {
                @Override
                public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
                    if (response.isSuccessful()){
                        if (response.body().isStatus()){
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            sessionManager.setKEY_NAMAPELANGGAN(response.body().getResultDataPelanggans().get(0).getNamaPelanggan());
                            sessionManager.setKEY_JEKEL(response.body().getResultDataPelanggans().get(0).getJekel());
                            sessionManager.setKEY_NOTELP(response.body().getResultDataPelanggans().get(0).getNoTelp());
                            sessionManager.setKEY_TANGGALUPDATE(response.body().getResultDataPelanggans().get(0).getTanggalUpdate());
                            sessionManager.setKEY_IDPELANGGAN(response.body().getResultDataPelanggans().get(0).getIdPelanggan());
                            sessionManager.setKEY_EMAIL(response.body().getResultDataPelanggans().get(0).getEmail());
                            sessionManager.setKEY_ALAMAT(response.body().getResultDataPelanggans().get(0).getAlamat());
                            Toast.makeText(LoginActivity.this, response.body().getResultDataPelanggans().get(0).getEmail(), Toast.LENGTH_SHORT).show();

                            startActivity(intent);
                        } else {

                            Toast.makeText(LoginActivity.this, "Password Salah", Toast.LENGTH_SHORT).show();
                        }
                    }
                }

                @Override
                public void onFailure(Call<ResponseLogin> call, Throwable t) {
                    Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

                }
            });

        } else {
            Toast.makeText(this, "Silahkan masukkan password", Toast.LENGTH_SHORT).show();
        }
    }
}
