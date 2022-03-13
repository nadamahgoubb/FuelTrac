package com.fueltrac.model.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.fueltrac.model.entities.Stations
import com.fueltrac.model.entities.tanks

@Dao
interface tanksDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun inserttank(tank: tanks)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun inserttanks(tank: List<tanks>?)

    @Query("SELECT * FROM tanks WHERE id =:id")
    fun gettankById(id: String?) : LiveData<tanks>

    @Query("SELECT * FROM tanks WHERE StationId =:stationid")
    fun gettanksByStationId(stationid: Int?) : LiveData<List<tanks>>
}