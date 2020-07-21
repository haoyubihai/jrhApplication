package com.example.jrhapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import com.example.jrhapplication.ktx.startKtxActivity
import com.example.jrhapplication.ui.*
import com.example.jrhapplication.ui.transform.TransformationLayoutActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    val aa = MutableLiveData<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnMaterialShapeDrawable.setOnClickListener { startKtxActivity<MaterialShapeDrawableActivity>() }
        btnChips.setOnClickListener { startKtxActivity<ChipsActivity>() }
        btnVp2.setOnClickListener { startKtxActivity<RecyclerviewVpActivity>() }
        btnShadow.setOnClickListener { startKtxActivity<ShadowActivity>() }
        btnBreath.setOnClickListener { startKtxActivity<BreathActivity>() }
        btnFresco.setOnClickListener { startKtxActivity<FrescoActivity>() }
        btnSpring.setOnClickListener { startKtxActivity<SpringActivity>() }
        btnTrans.setOnClickListener { startKtxActivity<TransformationLayoutActivity>() }
        btnFastJson.setOnClickListener { startKtxActivity<FastjsonActivity>() }
        btnMotion.setOnClickListener { startKtxActivity<MotionActivity>() }
        btnTimer.setOnClickListener { startKtxActivity<TimerActivity>() }

    }

}



