package com.fueltrac.model.network

import android.app.Activity
import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class ApiClient {

    private var retrofit: Retrofit? = null
    private val REQUEST_TIMEOUT :Long = 30


    fun getClient( /*String portNumber*/
        base: ApiBase
    ): Retrofit? {
        val gson: Gson = GsonBuilder()
            .setLenient()
            .create()
        val okHttpClient: OkHttpClient = OkHttpClient().newBuilder()
            .connectTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
            .build()
        if (retrofit == null) {
            val url: String = base.toString()
            Log.e("url**", url)
            retrofit = Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .client(okHttpClient)
                .build()
        }
        return retrofit
    }
}