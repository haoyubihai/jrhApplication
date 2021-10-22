package com.example.jrhapplication

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
}


