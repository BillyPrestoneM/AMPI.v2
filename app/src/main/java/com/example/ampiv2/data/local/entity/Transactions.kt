package com.example.ampiv2.data.local.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "transactions")
@Parcelize
data class Transactions(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val title : String = "",
    val type : String = "",
    val category : String = "",
    val description : String = "",
    val date : String = "",
    val amount : Double = 0.0,
    val day : Int = 0,
    val month : Int = 0,
    val year : Int = 0,
    val createdAt : Long = System.currentTimeMillis()
) : Parcelable