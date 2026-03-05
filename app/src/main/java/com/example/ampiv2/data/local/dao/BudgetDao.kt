package com.example.ampiv2.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.ampiv2.data.local.entity.MonthlyBudget

@Dao
interface BudgetDao {

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBudget(budget: MonthlyBudget)

    @Update
    suspend fun updateBudget(budget: MonthlyBudget)

    @Query("SELECT * FROM monthly_budget WHERE month = :month AND year = :year LIMIT 1")
    suspend fun getBudget(month: Int, year: Int): MonthlyBudget?

}