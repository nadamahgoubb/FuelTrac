package com.fueltrac.model.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.fueltrac.model.entities.Stations

@Dao
interface StationsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStation(station: Stations)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStations(station: List<Stations>?)

    @Query("SELECT * FROM stations WHERE UserId =:id")
    fun getStationById(id: String?) : LiveData<List<Stations>>

}