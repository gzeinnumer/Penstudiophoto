package com.mobileyuone2.penstudiophoto.model;

import com.google.gson.annotations.SerializedName;

public class ResultDataPelanggansItem {

	@SerializedName("nama_pelanggan")
	private String namaPelanggan;

	@SerializedName("jekel")
	private String jekel;

	@SerializedName("password")
	private String password;

	@SerializedName("no_telp")
	private String noTelp;

	@SerializedName("tanggal_update")
	private String tanggalUpdate;

	@SerializedName("id_pelanggan")
	private String idPelanggan;

	@SerializedName("email")
	private String email;

	@SerializedName("alamat")
	private String alamat;

	public void setNamaPelanggan(String namaPelanggan){
		this.namaPelanggan = namaPelanggan;
	}

	public String getNamaPelanggan(){
		return namaPelanggan;
	}

	public void setJekel(String jekel){
		this.jekel = jekel;
	}

	public String getJekel(){
		return jekel;
	}

	public void setPassword(String password){
		this.password = password;
	}

	public String getPassword(){
		return password;
	}

	public void setNoTelp(String noTelp){
		this.noTelp = noTelp;
	}

	public String getNoTelp(){
		return noTelp;
	}

	public void setTanggalUpdate(String tanggalUpdate){
		this.tanggalUpdate = tanggalUpdate;
	}

	public String getTanggalUpdate(){
		return tanggalUpdate;
	}

	public void setIdPelanggan(String idPelanggan){
		this.idPelanggan = idPelanggan;
	}

	public String getIdPelanggan(){
		return idPelanggan;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}

	public void setAlamat(String alamat){
		this.alamat = alamat;
	}

	public String getAlamat(){
		return alamat;
	}
}