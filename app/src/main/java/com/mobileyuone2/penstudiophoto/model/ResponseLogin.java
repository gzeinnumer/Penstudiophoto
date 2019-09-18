package com.mobileyuone2.penstudiophoto.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseLogin{

	@SerializedName("pesan")
	private String pesan;

	@SerializedName("result_data_pelanggans")
	private List<ResultDataPelanggansItem> resultDataPelanggans;

	@SerializedName("status")
	private boolean status;

	public void setPesan(String pesan){
		this.pesan = pesan;
	}

	public String getPesan(){
		return pesan;
	}

	public void setResultDataPelanggans(List<ResultDataPelanggansItem> resultDataPelanggans){
		this.resultDataPelanggans = resultDataPelanggans;
	}

	public List<ResultDataPelanggansItem> getResultDataPelanggans(){
		return resultDataPelanggans;
	}

	public void setStatus(boolean status){
		this.status = status;
	}

	public boolean isStatus(){
		return status;
	}
}