package com.example.jrhapplication.ui

import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.AbsoluteSizeSpan
import android.text.style.StyleSpan
import androidx.core.text.bold
import com.example.jrhapplication.R
import com.example.jrhapplication.ktx.dp2px
import com.jrhlive.library.dp2px
import kotlinx.android.synthetic.main.activity_ktx.*

class KtxActivity : BaseActivity() {

    val fontSize = dp2px(11)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ktx)

        tvContent1.text = createOutTime()
        tvContent2.text = createOutTime2()

    }

    private fun createOutTime(): SpannableStringBuilder {
        return SpannableStringBuilder("提交(逾期)").apply {
            setSpan(StyleSpan(Typeface.BOLD), 0, 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            setSpan(StyleSpan(Typeface.NORMAL), 2, length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            setSpan(
                AbsoluteSizeSpan(fontSize),
                2,
                length,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )

        }
    }

    private fun createOutTime2():SpannableStringBuilder {
        return SpannableStringBuilder("提交").apply {
            bold {
                append("(逾期)")
            }

        }
    }
}