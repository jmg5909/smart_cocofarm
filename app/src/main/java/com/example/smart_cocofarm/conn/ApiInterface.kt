package com.example.smart_cocofarm.conn

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {
    @FormUrlEncoded
    @POST
    fun getPost(@Url url: String, @FieldMap params: HashMap<String, Any>): Call<String>

    @GET("/image/{filename}")
    fun getImage(@Path("filename") filename: String): Call<ResponseBody>

    @GET
    fun getGet(@Url url: String): Call<String>



}