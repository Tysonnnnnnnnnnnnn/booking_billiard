package com.alex.goldenbreak.api

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "http://10.0.2.2:8000/api/"

    val instance: ApiService by lazy {
        // Tambahkan interceptor untuk memaksa header JSON
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor { chain ->
                val request = chain.request().newBuilder()
                    .addHeader("Accept", "application/json") // <--- KUNCI UTAMA
                    .build()
                chain.proceed(request)
            }.build()

        val gson = GsonBuilder()
            .setLenient()
            .create()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient) // Pasang okHttpClient di sini
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        retrofit.create(ApiService::class.java)
    }
}