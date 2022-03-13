package com.fueltrac.model.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.fueltrac.model.entities.TransactionModel

@Dao
interface TransactionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTransactionData(transactionModel: TransactionModel)

    @Query("SELECT * FROM TransactionsTable WHERE PlateNumber =:plateNum")
    fun getTransactionByPlate(plateNum: String?) : LiveData<TransactionModel>

    @Query("SELECT * FROM TransactionsTable WHERE userID =:tonken")
    fun getTransactionsByToken(tonken: Int?) : LiveData<List<TransactionModel>>

    @Delete
    fun deleteTransactionsByid (transactionModel: TransactionModel)
}