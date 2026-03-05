package com.example.ampiv2.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface TransactionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTransaction(transaction: Transaction)

    @Delete
    suspend fun deleteTransaction(transaction: Transaction)

    @Update
    suspend fun updateTransaction(transaction: Transaction)


    @Query("SELECT * FROM transactions WHERE day = :day AND month = :month AND year = :year")
    suspend fun getTransactionsForDay(day: Int, month: Int, year: Int): Flow<List<Transaction>>

    @Query("SELECT * FROM transactions WHERE month = :month AND year = :year")
    suspend fun getTransactionsForMonth(month: Int, year: Int): Flow<List<Transaction>>

    @Query("SELECT * FROM transactions WHERE year = :year")
    suspend fun getTransactionsForYear(year: Int): Flow<List<Transaction>>

    //Expenses Query
    @Query("SELECT SUM(amount) FROM transactions WHERE type = 'Pengeluaran' AND day = :day AND month = :month AND year = :year")
    suspend fun getTotalExpenseForDay(day : Int,month: Int, year: Int): Double?

    @Query("SELECT SUM(amount) FROM transactions WHERE type = 'Pengeluaran' AND month = :month AND year = :year")
    suspend fun getTotalExpenseForMonth(month: Int, year: Int): Double?

    @Query("SELECT SUM(amount) FROM transactions WHERE type = 'Pengeluaran'AND year = :year")
    suspend fun getTotalExpenseForYear(year: Int): Double?

    //Income Query
    @Query("SELECT SUM(amount) FROM transactions WHERE type = 'Pemasukkan' AND day = :day AND month = :month AND year = :year")
    suspend fun getTotalIncomeForDay(day : Int,month: Int, year: Int): Double?

    @Query("SELECT SUM(amount) FROM transactions WHERE type = 'Pemasukkan' AND month = :month AND year = :year")
    suspend fun getTotalIncomeByMonth(month: Int, year: Int): Double?

    @Query("SELECT SUM(amount) FROM transactions WHERE type = 'Pemasukkan' AND year = :year")
    suspend fun getTotalIncomeByYear(year: Int): Double?



}