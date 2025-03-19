package com.example.bsdifflibrary

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit


object RetrofitManager {
    val mOkHttpClient = OkHttpClient.Builder().callTimeout(10, TimeUnit.SECONDS)
        .build()

    var mRetrofit: Retrofit? = null

    fun initRetorfit(): RetrofitManager {
        mRetrofit = Retrofit.Builder().baseUrl(Constant.HTTP_URI).client(mOkHttpClient).build()
        return this
    }

    fun <T> getService(serviceClass: Class<T>) : T{
        return mRetrofit!!.create(serviceClass)
    }
}