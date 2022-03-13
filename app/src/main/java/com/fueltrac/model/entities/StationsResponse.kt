package com.fueltrac.model.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

class StationsResponse {

    @SerializedName("id")
    var id: Int? = null

    @SerializedName("stationName")
    var stationName: String? = null

    @SerializedName("tanks")
    var tanks: List<tanks>? = null

    @SerializedName("UserId")
    var UserId: Int? = null

    @SerializedName("pumps")
    var pumps: List<pumps>? = null

    constructor(
        id: Int?,
        stationName: String?,
        tanks: List<tanks>?,
        UserId: Int?,
        pumps: List<pumps>?
    ) {
        this.id = id
        this.stationName = stationName
        this.tanks = tanks
        this.UserId = UserId
        this.pumps = pumps
    }


}