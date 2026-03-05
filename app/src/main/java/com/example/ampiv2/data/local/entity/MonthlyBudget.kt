package com.example.ampiv2.data.local.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "monthly_budget")
@Parcelize
data class MonthlyBudget (
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val month : Int = 0,
    val year : Int = 0,
    val limitBudget : Double = 0.0,
    val createdAt : Long = System.currentTimeMillis()
) : Parcelable