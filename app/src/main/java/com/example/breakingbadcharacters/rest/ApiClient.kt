package com.example.breakingbadcharacters.rest

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    val client = OkHttpClient.Builder().build()
    val baseUrl = "https://www.breakingbadapi.com/api/"
    val gson =GsonBuilder()
        .setLenient()
        .create()

    val instance by lazy {
        val retrofit =Retrofit.Builder()
            .client(client)
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        retrofit.create(ApiInterface::class.java)
    }


}