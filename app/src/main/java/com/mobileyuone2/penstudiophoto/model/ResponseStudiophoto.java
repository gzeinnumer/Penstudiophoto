package com.mobileyuone2.penstudiophoto.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseStudiophoto{

	@SerializedName("pesan")
	private String pesan;

	@SerializedName("result_data_studiophotos")
	private List<ResultDataStudiophotosItem> resultDataStudiophotos;

	@SerializedName("status")
	private boolean status;

	public void setPesan(String pesan){
		this.pesan = pesan;
	}

	public String getPesan(){
		return pesan;
	}

	public void setResultDataStudiophotos(List<ResultDataStudiophotosItem> resultDataStudiophotos){
		this.resultDataStudiophotos = resultDataStudiophotos;
	}

	public List<ResultDataStudiophotosItem> getResultDataStudiophotos(){
		return resultDataStudiophotos;
	}

	public void setStatus(boolean status){
		this.status = status;
	}

	public boolean isStatus(){
		return status;
	}
}