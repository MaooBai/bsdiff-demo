package com.example.bsdiffdemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.TextView
import androidx.core.content.FileProvider
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.example.bsdiffdemo.databinding.ActivityMainBinding
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.io.OutputStream
import com.example.bsdifflibrary.InstallApkUtil
import com.example.bsdifflibrary.bsdiffUtil

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var outputStream : OutputStream? = null
    private var inputStream : InputStream? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Example of a call to a native method
        binding.sampleText.text = stringFromJNI()

        val viewModle = MainViewModle()
        viewModle.testLiveData.observe(
            this
        ) { value -> binding.sampleText.text = value }

        binding.editView.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                viewModle.changerData(p0?.trim().toString())
            }

        }
        )

        binding.button.setOnClickListener{
            viewModle.bsDiff()
        }

        binding.button2.setOnClickListener{
            InstallApkUtil.init(baseContext)
            InstallApkUtil.install()
        }

        viewModle.DownloadLiveData.observe(this,object : Observer<ResponseBody>{
            override fun onChanged(value: ResponseBody) {
                Log.e("TAG", "download is finish  start install in disk")
                viewModle.DiskUtil(value)
            }
        })
    }

    /**
     * A native method that is implemented by the 'bsdiffdemo' native library,
     * which is packaged with this application.
     */
    external fun stringFromJNI(): String

    companion object {
        // Used to load the 'bsdiffdemo' library on application startup.
        init {
            System.loadLibrary("bsdiffdemo")
        }
    }
}