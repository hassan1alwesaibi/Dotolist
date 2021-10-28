package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import android.os.Handler

class splashActivity : AppCompatActivity(){
    private var mDelayHandler: Handler? = null
    private val splashLong: Long = 2000 //2seconds
    internal val mRunnable: Runnable = Runnable {
        if (!isFinishing) {

            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_layout)
        mDelayHandler = Handler()

        mDelayHandler!!.postDelayed(mRunnable, splashLong)
    }
        public override fun onDestroy() {

            if (mDelayHandler != null) {
                mDelayHandler!!.removeCallbacks(mRunnable)
            }

            super.onDestroy()
        }

    }
