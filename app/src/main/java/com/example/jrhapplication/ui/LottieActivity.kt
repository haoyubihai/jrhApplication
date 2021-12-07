package com.example.jrhapplication.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.airbnb.lottie.LottieDrawable
import com.example.jrhapplication.R
import kotlinx.android.synthetic.main.activity_lottie.*

class LottieActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lottie)

        iv_pet?.run {


            setAnimation("24a6f08d-def5-46e2-aa89-f155be39a8ff_info_s=215661.json")
            repeatMode = LottieDrawable.RESTART
            playAnimation()

        }
    }
}