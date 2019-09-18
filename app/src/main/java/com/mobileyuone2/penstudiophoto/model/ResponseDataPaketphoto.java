package com.mobileyuone2.penstudiophoto.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class ResponseDataPaketphoto{

	@SerializedName("pesan")
	private String pesan;

	@SerializedName("result_data_paketphotos")
	private List<ResultDataPaketphotosItem> resultDataPaketphotos;

	@SerializedName("status")
	private boolean status;

	public void setPesan(String pesan){
		this.pesan = pesan;
	}

	public String getPesan(){
		return pesan;
	}

	public void setResultDataPaketphotos(List<ResultDataPaketphotosItem> resultDataPaketphotos){
		this.resultDataPaketphotos = resultDataPaketphotos;
	}

	public List<ResultDataPaketphotosItem> getResultDataPaketphotos(){
		return resultDataPaketphotos;
	}

	public void setStatus(boolean status){
		this.status = status;
	}

	public boolean isStatus(){
		return status;
	}
}