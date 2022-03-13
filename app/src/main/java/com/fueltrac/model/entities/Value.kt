package com.fueltrac.model.entities

import com.google.gson.annotations.SerializedName

data class Value(
    @SerializedName("dateTime")
    var dateTime: String ,
    @SerializedName("fuelLevel")
    var fuelLevel: String ,
    @SerializedName("fuelVolume")
    var fuelVolume: String ,
    @SerializedName("waterLevel")
    var waterLevel: String ,
    @SerializedName("waterVolume")
    var waterVolume: String ,
    @SerializedName("ullage")
    var ullage: String ,
    @SerializedName("temperature")
    var temperature: String ,
    @SerializedName("temperatureCompensatedVolume")
    var temperatureCompensatedVolume: String ,
    @SerializedName("dataSource")
    var dataSource: String ,
    @SerializedName("comment")
    var comment: String ,
    var  isHeader :Boolean = false
)  
