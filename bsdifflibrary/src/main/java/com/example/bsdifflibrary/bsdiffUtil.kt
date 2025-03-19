package com.example.bsdifflibrary

import java.io.File


object bsdiffUtil {
    external fun bsdiffPatch(BaseApk: String, patch: String, NewApk: String)

    fun init(
        APK_NAME: String = "base.apk",
        New_APK_Name : String = "new.apk",
        PATCH: String = "patch",
        BASE_APK_PATH: String = "sdcard/Download/",
        PATCH_PATH: String = "sdcard/Download/",
        NEW_APK_PATH: String = "sdcard/Download/"
    ) {
        Constant.APK_NAME = APK_NAME
        Constant.PATCH = PATCH
        Constant.BASE_APK_PATH = BASE_APK_PATH
        Constant.PATCH_PATH = PATCH_PATH
        Constant.NEW_APK_PATH = NEW_APK_PATH
        Constant.NEW_APK_NAME= New_APK_Name
    }

    suspend fun startBsdiff() : Boolean{
        val baseAPkPath = Constant.BASE_APK_PATH + Constant.APK_NAME
        val patchPath = Constant.PATCH_PATH + Constant.PATCH
        val newApk = Constant.NEW_APK_PATH + Constant.NEW_APK_NAME

        bsdiffPatch(baseAPkPath, patchPath, newApk)
        return File(newApk).exists()
    }
}