package com.fueltrac.model.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.fueltrac.model.dao.*
import com.fueltrac.model.entities.*

@Database(entities = [TransactionModel::class,User::class,Stations::class, tanks::class,pumps::class], version = 1, exportSchema = false)
abstract class GeneralDataBase : RoomDatabase() {

    abstract fun transactionDao() : TransactionDao
    abstract fun userDao() : UserDao
    abstract fun stationDao() : StationsDao
    abstract fun tanksDao() : tanksDao
    abstract fun pumpsDao() : pumpsDao

    companion object {

        @Volatile
        private var INSTANCE: GeneralDataBase? = null

        fun getDataClient(context: Context) : GeneralDataBase {

            if (INSTANCE != null) return INSTANCE!!

            synchronized(this) {

                INSTANCE = Room
                    .databaseBuilder(context, GeneralDataBase::class.java, "LOGIN_DATABASE")
                    .fallbackToDestructiveMigration()
                    .build()

                return INSTANCE!!

            }
        }

    }


}