package com.mobileyuone2.penstudiophoto.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseDataJenisPaket{

	@SerializedName("pesan")
	private String pesan;

	@SerializedName("result_data_jenispakets")
	private List<ResultDataJenispaketsItem> resultDataJenispakets;

	@SerializedName("status")
	private boolean status;

	public void setPesan(String pesan){
		this.pesan = pesan;
	}

	public String getPesan(){
		return pesan;
	}

	public void setResultDataJenispakets(List<ResultDataJenispaketsItem> resultDataJenispakets){
		this.resultDataJenispakets = resultDataJenispakets;
	}

	public List<ResultDataJenispaketsItem> getResultDataJenispakets(){
		return resultDataJenispakets;
	}

	public void setStatus(boolean status){
		this.status = status;
	}

	public boolean isStatus(){
		return status;
	}
}