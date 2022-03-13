package com.fueltrac.model.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.fueltrac.model.entities.TransactionModel
import com.fueltrac.model.entities.User

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User)

    @Query("SELECT * FROM User WHERE userId =:Id")
    fun getUserById(Id: Int?) : LiveData<User>
}