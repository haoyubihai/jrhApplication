package com.example.jrhapplication.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.jrhapplication.R
import com.example.jrhapplication.viewmodel.SkillViewModel
import kotlinx.android.synthetic.main.activity_motion.*
import kotlinx.android.synthetic.main.activity_skills_main.*
import kotlin.properties.Delegates

class SkillsMainActivity : AppCompatActivity() {

    private val skillViewModel by lazy { SkillViewModel() }

    private  var name: String by Delegates.notNull()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_skills_main)

        /**
         * expression
         *
         * Process: com.example.jrhapplication, PID: 20757
         * java.util.NoSuchElementException: List is empty.
         * at kotlin.collections.CollectionsKt___CollectionsKt.first(_Collections.kt:206)
         */
        btn.setOnClickListener { textView.text = skillViewModel.testFirstUser().address }

        btn2.setOnClickListener { textView.text = skillViewModel.testFirstNullUser()?.address?:"empty" }

        testParseToInt()


    }

    fun testParseToInt(){
        val a = "aaa"
        println("----a=${a.toFloatOrNull()}")
    }


}

class KsListener :View.OnLongClickListener{

    override fun onLongClick(v: View?): Boolean {
        return true
    }
}