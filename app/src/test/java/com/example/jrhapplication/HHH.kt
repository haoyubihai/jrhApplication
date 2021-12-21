package com.example.jrhapplication

import org.json.JSONObject

class HHH {
}


class AAA{
    var id: String?=null
    get() = if (field.isNullOrEmpty()) roleId else field

    var roleId:String?=null
        get() = if (field.isNullOrEmpty()) id else field
}



fun main() {
    println("Int.max=${Int.MAX_VALUE}")

    try {
        JSONObject().apply {
            put("a", 1)
            put("b", "2")
        }.let {
            val a = it.optString("a")
            val b = it.optString("b")
            println("a=$a---b=$b")
        }
    }catch (e: Exception) {
        e.printStackTrace()
    }

}


