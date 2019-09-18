package com.mobileyuone2.penstudiophoto.model;

import com.google.gson.annotations.SerializedName;

public class ResultDataPaketphotosItem{

	@SerializedName("nama_paketphoto")
	private String namaPaketphoto;

	@SerializedName("id")
	private String id;

	@SerializedName("tanggal_update")
	private String tanggalUpdate;

	@SerializedName("id_paketphoto")
	private String idPaketphoto;

	@SerializedName("id_jenispaket")
	private String idJenispaket;

	public void setNamaPaketphoto(String namaPaketphoto){
		this.namaPaketphoto = namaPaketphoto;
	}

	public String getNamaPaketphoto(){
		return namaPaketphoto;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setTanggalUpdate(String tanggalUpdate){
		this.tanggalUpdate = tanggalUpdate;
	}

	public String getTanggalUpdate(){
		return tanggalUpdate;
	}

	public void setIdPaketphoto(String idPaketphoto){
		this.idPaketphoto = idPaketphoto;
	}

	public String getIdPaketphoto(){
		return idPaketphoto;
	}

	public void setIdJenispaket(String idJenispaket){
		this.idJenispaket = idJenispaket;
	}

	public String getIdJenispaket(){
		return idJenispaket;
	}
}