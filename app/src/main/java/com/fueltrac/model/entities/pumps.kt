package com.fueltrac.model.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "pumps")
class pumps {


    constructor(id: Int?, pumpName: String?, StationId: Int?) {
        this.id = id
        this.pumpName = pumpName
        this.StationId = StationId
    }

    @SerializedName("id")
    @PrimaryKey
    var id: Int? = null

    @SerializedName("pumpName")
    var pumpName: String? = null

    @SerializedName("StationId")
    var StationId: Int? = null

    override fun toString(): String {
        if (pumpName.equals(null)){
            return "No Data"
        }else {
            return pumpName!!
        }
    }
}