package com.example.smart_cocofarm.conn

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

object Service {
    private const val BASE_URL = "http://192.168.0.202:9090/"

    private var retrofit: Retrofit? = null

    fun getApiClient(): Retrofit {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .client(OkHttpClient.Builder().readTimeout(10, TimeUnit.SECONDS).build())
                .build()
        }
        return retrofit!!
    }
}