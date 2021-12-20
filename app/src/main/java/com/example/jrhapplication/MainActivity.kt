package com.example.jrhapplication

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import com.example.jrhapplication.databinding.ActivityMainBinding
import com.example.jrhapplication.flowtest.FlowActivity
import com.example.jrhapplication.ktx.startKtxActivity
import com.example.jrhapplication.ui.*
import com.example.jrhapplication.ui.transform.TransformationLayoutActivity
import com.jrhlive.library.viewBinding
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    val aa = MutableLiveData<String>()

    private val binding by viewBinding(ActivityMainBinding::bind)






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
        btnLiveData.setOnClickListener { startKtxActivity<MainLiveDataActivity>() }
        btnKTX.setOnClickListener { startKtxActivity<KtxActivity>() }
        btnFlow.setOnClickListener { startKtxActivity<FlowActivity>() }
        btnSkill.setOnClickListener { startKtxActivity<SkillsMainActivity>() }
        btnScroll.setOnClickListener { startKtxActivity<ScrollingActivity>() }
        btnMotionLay.setOnClickListener { startKtxActivity<MotionMainActivity>() }
        btnRxLay.setOnClickListener { startKtxActivity<RxActivity>() }
        btnMessageBarrier.setOnClickListener { startKtxActivity<MessageBarrierActivity>() }
        atmosphere.setOnClickListener { startKtxActivity<AtmosphereAct>() }
        lottie.setOnClickListener { startKtxActivity<LottieActivity>() }
        compose.setOnClickListener { startKtxActivity<ComposeActivity>() }
        widget.setOnClickListener { startKtxActivity<WidgetActivity>() }



    }

    override fun onResume() {
        super.onResume()
        println("onResume-----------")
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        println("onAttachedToWindow-----------")
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        println("onWindowFocusChanged------hasFocus=$hasFocus---")
    }

    override fun onWindowAttributesChanged(params: WindowManager.LayoutParams?) {
        super.onWindowAttributesChanged(params)
        println("onWindowAttributesChanged---params=$params--------")
    }

}



