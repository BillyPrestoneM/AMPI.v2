package com.example.ampiv2.data.local.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "categories")
@Parcelize
data class Category (
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val name : String = "",
    val type : String = "",
) : Parcelable