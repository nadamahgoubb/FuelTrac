package com.fueltrac.model.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "tanks")
class tanks {

    constructor(id: Int?, tankName: String?, StationId: Int?) {
        this.id = id
        this.tankName = tankName
        this.StationId = StationId
    }

    @SerializedName("id")
    @PrimaryKey
    var id: Int? = null

    @SerializedName("tankName")
    var tankName: String? = null

    @SerializedName("StationId")
    var StationId: Int? = null




}