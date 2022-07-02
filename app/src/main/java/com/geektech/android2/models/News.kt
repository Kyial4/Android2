package com.geektech.android2.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class News(
    @PrimaryKey(autoGenerate = true)
    var id:Int,
    var tittle: String,
    val createdAt: Long
):Serializable {
}
