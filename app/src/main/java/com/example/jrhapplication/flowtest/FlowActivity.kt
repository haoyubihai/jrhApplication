package com.example.jrhapplication.flowtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.jrhapplication.R
import kotlinx.android.synthetic.main.activity_flow.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.launch

class FlowActivity : AppCompatActivity() {

    private val viewModel by lazy { FlowViewModel() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flow)

        submit.setOnClickListener {
            viewModel.submit()
        }

        submitSame.setOnClickListener {
            viewModel.submitSame()
        }

        submitObject.setOnClickListener {
            viewModel.submitObjectValue()
        }

        viewModel.uiState.onEachEvent {
                when (it) {
                    is UiState.ContentShow -> {
                        tvContent.text = it.content + "${System.currentTimeMillis()}"
                    }
                    is UiState.SubmitShow -> {
                        tvContent.text = "object---${System.currentTimeMillis()}"

                    }
                }
        }.launchIn(lifecycleScope)

    }
}
