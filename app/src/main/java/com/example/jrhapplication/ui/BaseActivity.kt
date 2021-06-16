package com.example.jrhapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.jrhapplication.R
import com.zj.easyfloat.EasyFloat

open class BaseActivity: AppCompatActivity() {

    var rootContent:View? = null
    var ksContent :ViewGroup?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initCommonContentView()
//        initFloatView()
    }

    private fun initFloatView() {
        EasyFloat
            .layout(R.layout.lay_float)
            .blackList(mutableListOf(ChipsActivity::class.java))
            .show(this)
    }

    private fun initCommonContentView() {
        val content = findViewById<ViewGroup>(android.R.id.content)
        content?.removeAllViews()
        rootContent = LayoutInflater.from(this).inflate(R.layout.base_lay_common,null,false)
        ksContent = rootContent?.findViewById(R.id.ksContent)
        content?.addView(rootContent)

    }



    override fun setContentView(layoutResID: Int) {
//
        ksContent?.let {
        it.addView(LayoutInflater.from(this).inflate(layoutResID,null,false))
        }?: kotlin.run {
            super.setContentView(layoutResID)
        }
    }

    override fun addContentView(view: View?, params: ViewGroup.LayoutParams?) {
        super.addContentView(view, params)
    }
    override fun setContentView(view: View?) {
//        super.setContentView(view)
        ksContent?.run {
            addView(view)
        }?: kotlin.run {
            super.setContentView(view)
        }
    }
}