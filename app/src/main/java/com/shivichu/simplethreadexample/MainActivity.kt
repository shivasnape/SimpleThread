package com.shivichu.simplethreadexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.shivichu.simplethreadexample.databinding.ActivityMainBinding
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding : ActivityMainBinding
    private var mStatus : Boolean = false
    private var mCounter : Int = 0
    private lateinit var mHandler : Handler
    private val TAG = MainActivity::class.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        mHandler = Handler(Looper.getMainLooper())

        mBinding.btnStart.setOnClickListener {
            Toast.makeText(applicationContext,"Clicked",Toast.LENGTH_SHORT).show()
            mStatus = true
            Thread(Runnable {
                while(mStatus) {
                    mCounter++
                    Thread.sleep(2000)
                    Log.i(TAG,"Counter Value -> $mCounter")
                    mHandler.post {
                        mBinding.textView.text = "Counter Value -> $mCounter"
                    }
                }
            }).start()
        }

        mBinding.btnStop.setOnClickListener {
            mStatus = false
        }
    }
}