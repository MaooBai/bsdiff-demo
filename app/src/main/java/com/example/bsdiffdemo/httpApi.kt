package com.example.bsdiffdemo

import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query

interface httpApi {
    @GET("system/testadapter/downloadApk")
    suspend fun getDownlaodApk(
        @Query("apkName") apkName : String
    ):ResponseBody


}