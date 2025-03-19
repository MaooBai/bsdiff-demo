package com.example.bsdiffdemo

import android.annotation.SuppressLint
import android.os.Environment
import android.util.Log
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.example.bsdifflibrary.bsdiffUtil.bsdiffPatch
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream

class MainViewModle : ViewModel(), LifecycleObserver{
    val testLiveData = MutableLiveData<String>()
    val DownloadLiveData = MutableLiveData<ResponseBody>()

    fun changerData(name : String){
        testLiveData.postValue("hello $name")
    }

    val mRetrofit = RetrofitManager().initRetorfit()
    val service = mRetrofit.getService(httpApi::class.java)

    fun getApk(){
        viewModelScope.launch {
          DownloadLiveData.postValue(service.getDownlaodApk("app-debug.apk"))
        }
    }



    @SuppressLint("SdCardPath")
    fun DiskUtil(value: ResponseBody){
        var outputStream : OutputStream? = null
        var inputStream : InputStream? = null
        val dataSpace = ByteArray(4096)
        inputStream = value.byteStream()
        outputStream = FileOutputStream(File("sdcard/Download/file/app-debug.apk"))
        var dataSize = inputStream!!.read(dataSpace)
        viewModelScope.launch {
            while (dataSize != -1){
                outputStream!!.write(dataSpace,0,dataSize)
                dataSize = inputStream!!.read(dataSpace)
            }
            outputStream!!.flush()
            inputStream!!.close()
            outputStream!!.close()
        }
    }


    fun bsDiff(){
        val patch : String = File(Environment.getExternalStorageDirectory(), "patch").absolutePath
        val oldApk = File(Environment.getExternalStorageDirectory(), "app-debug.apk").absolutePath
        val newApk = createNewApk().absolutePath

        viewModelScope.launch {
            bsdiffPatch(oldApk, patch, newApk)
        }
    }


    private fun createNewApk() : File{
        val newApk = File(Environment.getExternalStorageDirectory(), "bsdiff.apk")
        if(!newApk.exists()){
            try{
                newApk.createNewFile()
            }catch (e : IOException){
                Log.e("TAG",e.message.toString())
            }
        }
        return newApk
    }

}