package com.example.ampiv2.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.Transaction
import com.example.ampiv2.data.local.dao.BudgetDao
import com.example.ampiv2.data.local.dao.TransactionDao
import com.example.ampiv2.data.local.entity.Category
import com.example.ampiv2.data.local.entity.MonthlyBudget

@Database(
    entities = [
        Transaction::class,
        Category::class,
        MonthlyBudget::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AmpiDatabase : RoomDatabase () {
    abstract fun transactionDao(): TransactionDao
    abstract fun budgetDao(): BudgetDao

    companion object {
        @Volatile
        private var INSTANCE: AmpiDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context): AmpiDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AmpiDatabase::class.java,
                    "ampidb"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}