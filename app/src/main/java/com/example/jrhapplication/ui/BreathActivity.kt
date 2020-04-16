package com.example.jrhapplication.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.jrhapplication.R
import kotlinx.android.synthetic.main.activity_brithe.*

class BreathActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_brithe)

        btnStart.setOnClickListener { breathView.startAni() }
        btnStop.setOnClickListener { breathView.stopAni() }

    }
}
