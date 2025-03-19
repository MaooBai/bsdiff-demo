package com.example.bsdiffdemo

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

class RetrofitManager {
    val mOkHttpClient = OkHttpClient.Builder().callTimeout(10, TimeUnit.SECONDS)
        .addInterceptor(HttpLoggingInterceptor { message ->
            Log.e(
                "TAG",
                message
            )
        }.setLevel(HttpLoggingInterceptor.Level.BODY)).build()

    var mRetrofit: Retrofit? = null

    fun initRetorfit(): RetrofitManager {
        mRetrofit = Retrofit.Builder().baseUrl("http://192.168.5.3/").client(mOkHttpClient).build()
        return this
    }

    fun <T> getService(serviceClass: Class<T>) : T{
        return mRetrofit!!.create(serviceClass)
    }
}