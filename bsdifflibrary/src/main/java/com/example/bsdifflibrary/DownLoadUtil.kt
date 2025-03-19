package com.example.bsdifflibrary

import androidx.annotation.RestrictTo
import androidx.lifecycle.LifecycleCoroutineScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.io.OutputStream
import kotlin.coroutines.CoroutineContext

object DownLoadUtil {
    val mRetrofit = RetrofitManager.initRetorfit()
    var mService = mRetrofit.getService(httpApi::class.java)

    suspend fun getDownApk(){
        DiskUtil( mService.getDownlaodApk(Constant.APK_NAME))
    }

    suspend fun DiskUtil(value: ResponseBody){
        var outputStream : OutputStream? = null
        var inputStream : InputStream? = null
        val dataSpace = ByteArray(4096)
        inputStream = value.byteStream()
        outputStream = FileOutputStream(File(Constant.PATCH_PATH + Constant.PATCH))
        var dataSize = inputStream!!.read(dataSpace)
        while (dataSize != -1){
            outputStream!!.write(dataSpace,0,dataSize)
            dataSize = inputStream!!.read(dataSpace)
        }
        outputStream!!.flush()
        inputStream!!.close()
        outputStream!!.close()
    }
}