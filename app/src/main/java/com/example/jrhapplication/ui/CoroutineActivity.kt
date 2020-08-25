package com.example.jrhapplication.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.example.jrhapplication.R
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.intrinsics.startCoroutineCancellable
import kotlinx.coroutines.launch
import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext

class CoroutineActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutine)
    }

    @InternalCoroutinesApi
    suspend fun hello() {
        suspend {
            println("hello")
        }.startCoroutineCancellable(object : Continuation<Unit> {

            override val context: CoroutineContext
                get() = TODO("Not yet implemented")

            override fun resumeWith(result: Result<Unit>) {
                TODO("Not yet implemented")
            }
        })

    }
}