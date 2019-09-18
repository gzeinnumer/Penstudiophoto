package com.mobileyuone2.penstudiophoto.helper;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {

    private String PREF_NAME = "SIMPAN";
    private String KEY_NAMAPELANGGAN = "namapelanggan";
    private String KEY_JEKEL = "jekel";
    private String KEY_NOTELP = "notelp";
    private String KEY_TANGGALUPDATE = "tanggalupdate";
    private String KEY_IDPELANGGAN = "idpelanggan";
    private String KEY_EMAIL = "email";
    private String KEY_ALAMAT = "alamat";

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    public SessionManager (Context context) {
        mSharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);

    }

    public void logout() {
        mEditor = mSharedPreferences.edit();
        mEditor.clear().commit();

    }

    public String getKEY_NAMAPELANGGAN() {
        return mSharedPreferences.getString(KEY_NAMAPELANGGAN, null);
    }

    public void setKEY_NAMAPELANGGAN(String s) {
        mEditor = mSharedPreferences.edit();
        mEditor.putString(KEY_NAMAPELANGGAN, s).apply();
    }

    public String getKEY_JEKEL() {
        return mSharedPreferences.getString(KEY_JEKEL, null);
    }

    public void setKEY_JEKEL(String s) {
        mEditor = mSharedPreferences.edit();
        mEditor.putString(KEY_JEKEL, s).apply();
    }

    public String getKEY_NOTELP() {
        return mSharedPreferences.getString(KEY_NOTELP, null);
    }

    public void setKEY_NOTELP(String s) {
        mEditor = mSharedPreferences.edit();
        mEditor.putString(KEY_NOTELP, s).apply();
    }

    public String getKEY_TANGGALUPDATE() {
        return mSharedPreferences.getString(KEY_TANGGALUPDATE, null);
    }

    public void setKEY_TANGGALUPDATE(String s) {
        mEditor = mSharedPreferences.edit();
        mEditor.putString(KEY_TANGGALUPDATE, s).apply();
    }

    public String getKEY_IDPELANGGAN() {
        return mSharedPreferences.getString(KEY_IDPELANGGAN, null);
    }

    public void setKEY_IDPELANGGAN(String s) {
        mEditor = mSharedPreferences.edit();
        mEditor.putString(KEY_IDPELANGGAN, s).apply();
    }

    public String getKEY_EMAIL() {
        return mSharedPreferences.getString(KEY_EMAIL, null);
    }

    public void setKEY_EMAIL(String s) {
        mEditor = mSharedPreferences.edit();
        mEditor.putString(KEY_EMAIL, s).apply();
    }

    public String getKEY_ALAMAT() {
        return mSharedPreferences.getString(KEY_ALAMAT, null);
    }

    public void setKEY_ALAMAT(String s) {
        mEditor = mSharedPreferences.edit();
        mEditor.putString(KEY_ALAMAT, s).apply();
    }
}
