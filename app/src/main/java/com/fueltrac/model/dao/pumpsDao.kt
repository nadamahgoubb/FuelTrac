package com.fueltrac.model.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.fueltrac.model.entities.Stations
import com.fueltrac.model.entities.pumps
import com.fueltrac.model.entities.tanks

@Dao
interface pumpsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPump(pump: pumps)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertpumps(pump: List<pumps>?)

    @Query("SELECT * FROM pumps WHERE id =:id")
    fun getpumpById(id: String?) : LiveData<pumps>

    @Query("SELECT * FROM pumps WHERE StationId =:stationid")
    fun getpumpByStationId(stationid: Int?) : LiveData<List<pumps>>
}