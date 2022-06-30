package com.geektech.android2.models

import java.io.Serializable

data class News(
    var tittle:String,
    val createdAt: Long
):Serializable
