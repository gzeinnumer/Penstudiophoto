package com.mobileyuone2.penstudiophoto.network;

import com.mobileyuone2.penstudiophoto.model.ResponseDataJenisPaket;
import com.mobileyuone2.penstudiophoto.model.ResponseDataRegister;
import com.mobileyuone2.penstudiophoto.model.ResponseLogin;
import com.mobileyuone2.penstudiophoto.model.ResponseReadDataPaketById;
import com.mobileyuone2.penstudiophoto.model.ResponseReadDataStudiophoto;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

//todo 3 buat class
public interface ApiService {

    //todo 4 buat function untuk login
    @FormUrlEncoded
    @POST("jsonstudiophoto/login")
    Call<ResponseLogin> login(@Field("email") String var1,
                              @Field("password") String var2);

    //todo 5 buat respons

    @GET ("jsonstudiophoto/getDataStudiophoto")
    Call<ResponseReadDataStudiophoto> getDataStudiophoto();

    @FormUrlEncoded
    @POST("jsonstudiophoto/getPaketphoto")
    Call<ResponseReadDataPaketById> paketphoto(@Field("id_paketphoto") String var1);

    @FormUrlEncoded
    @POST("jsonstudiophoto/register")
    Call<ResponseDataRegister> register(@Field("nama_pelanggan") String var1,
                                     @Field("jekel") String var2,
                                     @Field("email") String var3,
                                     @Field("password") String var4,
                                     @Field("no_telp") String var5,
                                     @Field("alamat") String var6);

    @FormUrlEncoded
    @POST("jsonstudiophoto/getDataJenisPakets")
    Call<ResponseDataJenisPaket> getDataJenisPaket(@Field("id_jenispaket") String var1);

    @FormUrlEncoded
    @POST("jsonstudiophoto/bookingFoto")
    Call<ResponseDataRegister> boking(@Field("id_studiophoto") String var1,
                                        @Field("id_pelanggan") String var2,
                                        @Field("id_paketphoto") String var3,
                                        @Field("id_jenispaket") String var4,
                                        @Field("tanggal_booking") String var5,
                                        @Field("tanggal_foto") String var6);

}
