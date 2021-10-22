package com.example.jrhapplication.ui

import android.app.Activity
import android.os.Bundle
import androidx.core.os.HandlerCompat.postDelayed
import com.example.jrhapplication.R
import kotlinx.android.synthetic.main.act_atmosphere.*
import kotlin.random.Random

class AtmosphereAct: Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_atmosphere)

        tvBtn.setOnClickListener {
            atmosphereView.startAni(Random.nextInt(6))
            it.postDelayed(
                {atmosphereView.startAni(Random.nextInt(6))},500L)
            it.postDelayed(
                {atmosphereView.startAni(Random.nextInt(6))},1500L)
        }

    }
}