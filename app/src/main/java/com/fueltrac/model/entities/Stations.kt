package com.fueltrac.model.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "stations")
class Stations {


    @SerializedName("id")
    @PrimaryKey
    var id: Int? = null

    @SerializedName("stationName")
    var stationName: String? = null

    @SerializedName("UserId")
    var UserId: Int? = null


    constructor(id: Int?, stationName: String?, UserId: Int?) {
        this.id = id
        this.stationName = stationName
        this.UserId = UserId
    }



}