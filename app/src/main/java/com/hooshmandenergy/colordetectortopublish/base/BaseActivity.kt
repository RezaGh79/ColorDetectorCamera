package com.hooshmandenergy.colordetectortopublish.base

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())

        initControls(savedInstanceState)
        initEvents()
    }


    abstract fun initControls(savedInstanceState: Bundle?)

    abstract fun initEvents()

    abstract fun getLayoutId(): Int
}