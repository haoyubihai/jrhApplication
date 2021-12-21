package com.example.jrhapplication

import org.json.JSONObject
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner




@RunWith(RobolectricTestRunner::class)
class  JsonTest{

    @Test
    fun main() {

        JSONObject().apply {
            put("a", 1)
            put("b", "2")
        }.let {
            val a = it.optString("a")
            val b = it.optString("b")
            println("a=$a---b=$b")
        }

    }
}


