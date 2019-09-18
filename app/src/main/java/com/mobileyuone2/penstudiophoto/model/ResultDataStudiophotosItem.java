package com.mobileyuone2.penstudiophoto.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class ResultDataStudiophotosItem implements Parcelable {

	@SerializedName("nama_studiophoto")
	private String namaStudiophoto;

	@SerializedName("id_pemilikstudiophoto")
	private String idPemilikstudiophoto;

	@SerializedName("foto_studiophoto")
	private String fotoStudiophoto;

	@SerializedName("keterangan")
	private String keterangan;

	@SerializedName("id_lokasi")
	private String idLokasi;

	@SerializedName("latitude")
	private String latitude;

	@SerializedName("tanggal_update")
	private String tanggalUpdate;

	@SerializedName("id_studiophoto")
	private String idStudiophoto;

	@SerializedName("id_paketphoto")
	private String idPaketphoto;

	@SerializedName("alamat")
	private String alamat;

	@SerializedName("longitude")
	private String longitude;

	protected ResultDataStudiophotosItem(Parcel in) {
		namaStudiophoto = in.readString();
		idPemilikstudiophoto = in.readString();
		fotoStudiophoto = in.readString();
		keterangan = in.readString();
		idLokasi = in.readString();
		latitude = in.readString();
		tanggalUpdate = in.readString();
		idStudiophoto = in.readString();
		idPaketphoto = in.readString();
		alamat = in.readString();
		longitude = in.readString();
	}

	public static final Creator<ResultDataStudiophotosItem> CREATOR = new Creator<ResultDataStudiophotosItem>() {
		@Override
		public ResultDataStudiophotosItem createFromParcel(Parcel in) {
			return new ResultDataStudiophotosItem(in);
		}

		@Override
		public ResultDataStudiophotosItem[] newArray(int size) {
			return new ResultDataStudiophotosItem[size];
		}
	};

	public void setNamaStudiophoto(String namaStudiophoto){
		this.namaStudiophoto = namaStudiophoto;
	}

	public String getNamaStudiophoto(){
		return namaStudiophoto;
	}

	public void setIdPemilikstudiophoto(String idPemilikstudiophoto){
		this.idPemilikstudiophoto = idPemilikstudiophoto;
	}

	public String getIdPemilikstudiophoto(){
		return idPemilikstudiophoto;
	}

	public void setFotoStudiophoto(String fotoStudiophoto){
		this.fotoStudiophoto = fotoStudiophoto;
	}

	public String getFotoStudiophoto(){
		return fotoStudiophoto;
	}

	public void setKeterangan(String keterangan){
		this.keterangan = keterangan;
	}

	public String getKeterangan(){
		return keterangan;
	}

	public void setIdLokasi(String idLokasi){
		this.idLokasi = idLokasi;
	}

	public String getIdLokasi(){
		return idLokasi;
	}

	public void setLatitude(String latitude){
		this.latitude = latitude;
	}

	public String getLatitude(){
		return latitude;
	}

	public void setTanggalUpdate(String tanggalUpdate){
		this.tanggalUpdate = tanggalUpdate;
	}

	public String getTanggalUpdate(){
		return tanggalUpdate;
	}

	public void setIdStudiophoto(String idStudiophoto){
		this.idStudiophoto = idStudiophoto;
	}

	public String getIdStudiophoto(){
		return idStudiophoto;
	}

	public void setIdPaketphoto(String idPaketphoto){
		this.idPaketphoto = idPaketphoto;
	}

	public String getIdPaketphoto(){
		return idPaketphoto;
	}

	public void setAlamat(String alamat){
		this.alamat = alamat;
	}

	public String getAlamat(){
		return alamat;
	}

	public void setLongitude(String longitude){
		this.longitude = longitude;
	}

	public String getLongitude(){
		return longitude;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(namaStudiophoto);
		dest.writeString(idPemilikstudiophoto);
		dest.writeString(fotoStudiophoto);
		dest.writeString(keterangan);
		dest.writeString(idLokasi);
		dest.writeString(latitude);
		dest.writeString(tanggalUpdate);
		dest.writeString(idStudiophoto);
		dest.writeString(idPaketphoto);
		dest.writeString(alamat);
		dest.writeString(longitude);
	}
}