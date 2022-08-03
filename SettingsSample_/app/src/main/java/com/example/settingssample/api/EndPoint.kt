package com.example.settingssample.api

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object EndPoint {
    fun apiService() : ApiService {
       return  Retrofit.Builder()
        .baseUrl("https://gorest.co.in/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build().create(ApiService::class.java)
    }
}