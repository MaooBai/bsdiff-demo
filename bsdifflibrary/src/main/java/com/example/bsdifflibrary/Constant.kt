package com.example.bsdifflibrary

import android.annotation.SuppressLint
import android.content.Context

class Constant {
    companion object{
        var BASE_APK_PATH = "sdcard/Download/"
        var PATCH_PATH = "sdcard/Download/"
        var NEW_APK_PATH = "sdcard/Download/"
        var APK_NAME = "base.apk"
        var PATCH = "patch"
        var NEW_APK_NAME = "new.apk"
        var HTTP_URI = "192.168.5.3:80/system/testadapter/downloadApk"
        var BASE_PATH = "sdcard/Download/"
        @JvmStatic
        var CONTEXT : Context? = null
    }
}