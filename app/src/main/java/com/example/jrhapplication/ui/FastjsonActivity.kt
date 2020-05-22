package com.example.jrhapplication.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.alibaba.fastjson.JSON
import com.example.jrhapplication.R
import groovy.json.JsonOutput

class FastjsonActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fastjson)

        val datas = mutableListOf<User>()
        repeat(5) {
            datas.add(User("name$it", it))
        }

        Log.e("FastJson =", JSON.toJSONString(datas))

        val str = """
            [{"age":0,"name":"name0"},{"age":1,"name":"name1"},{"age":2,"name":"name2"},{"age":3,"name":"name3"},{"age":4,"name":"name4"}]
        """.trimIndent()

//        val users = JSON.parse(str) as MutableList<User>

//        users.forEach {
//            Log.e("FastJson USER=",it.toString())
//        }

        Log.e("JSON----", JsonOutput.prettyPrint(str))

    }
}

data class User(val name: String, val age: Int)