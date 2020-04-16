package com.example.jrhapplication.ui.transform

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.jrhapplication.R
import com.example.jrhapplication.bean.MockUtil
import com.example.jrhapplication.bean.Poster
import com.example.jrhapplication.ui.transform.TransformAdapter
import kotlinx.android.synthetic.main.activity_transformation_layout.*

class TransformationLayoutActivity : AppCompatActivity() {

    lateinit var  mAdapter: TransformAdapter

    val datas = mutableListOf<Poster>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transformation_layout)

        fab.setOnClickListener {
            transformationLayout.startTransform()
        }
        myCardView.setOnClickListener {
            transformationLayout.finishTransform()
        }


        mAdapter =
            TransformAdapter(datas)
        rcList.adapter =mAdapter
        datas.clear()
        datas.addAll(MockUtil.getMockPosters())
        mAdapter.setNewInstance(datas)


    }
}
