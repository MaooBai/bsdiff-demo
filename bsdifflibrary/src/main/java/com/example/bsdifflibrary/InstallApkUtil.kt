package com.example.bsdifflibrary

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.content.FileProvider
import java.io.File

object InstallApkUtil {
    fun init(context : Context){
        Constant.CONTEXT = context
    }

    fun install(){
        if(Constant.CONTEXT == null){
            Log.e("InstallApkUtil", "请先初始化")
            return
        }
        val provider = FileProvider.getUriForFile(
            Constant.CONTEXT!!,
            Constant.CONTEXT!!.packageName + ".provider",
            File(Constant.NEW_APK_PATH + Constant.APK_NAME)
        )
        val intent = Intent(Intent.ACTION_VIEW)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.addCategory(Intent.CATEGORY_DEFAULT)
        intent.setDataAndType(provider,"application/vnd.android.package-archive")
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        Constant.CONTEXT!!.startActivity(intent)
    }
}