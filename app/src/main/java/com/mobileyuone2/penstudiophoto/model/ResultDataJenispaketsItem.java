package com.mobileyuone2.penstudiophoto.model;

import com.google.gson.annotations.SerializedName;

public class ResultDataJenispaketsItem{

	@SerializedName("nama_jenispaket")
	private String namaJenispaket;

	@SerializedName("harga")
	private String harga;

	@SerializedName("id")
	private String id;

	@SerializedName("deskripsi")
	private String deskripsi;

	@SerializedName("tanggal_update")
	private String tanggalUpdate;

	@SerializedName("id_jenispaket")
	private String idJenispaket;

	public void setNamaJenispaket(String namaJenispaket){
		this.namaJenispaket = namaJenispaket;
	}

	public String getNamaJenispaket(){
		return namaJenispaket;
	}

	public void setHarga(String harga){
		this.harga = harga;
	}

	public String getHarga(){
		return harga;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setDeskripsi(String deskripsi){
		this.deskripsi = deskripsi;
	}

	public String getDeskripsi(){
		return deskripsi;
	}

	public void setTanggalUpdate(String tanggalUpdate){
		this.tanggalUpdate = tanggalUpdate;
	}

	public String getTanggalUpdate(){
		return tanggalUpdate;
	}

	public void setIdJenispaket(String idJenispaket){
		this.idJenispaket = idJenispaket;
	}

	public String getIdJenispaket(){
		return idJenispaket;
	}
}