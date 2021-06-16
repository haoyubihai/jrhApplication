package com.example.jrhapplication.ui

import android.graphics.Outline
import android.graphics.Path
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewOutlineProvider
import androidx.annotation.RequiresApi
import com.example.jrhapplication.R
import com.jrhlive.library.dp2px
import kotlinx.android.synthetic.main.activity_shadow.*

class ShadowActivity : BaseActivity() {

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shadow)



    }
}
