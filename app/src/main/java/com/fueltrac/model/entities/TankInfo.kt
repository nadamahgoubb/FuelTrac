package com.fueltrac.model.entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class TankInfo(
    @SerializedName("color")
    @Expose
    var  color:String,
@SerializedName("tankId")
@Expose
var tankId :Int ,
@SerializedName("name")
@Expose
var name : String ,
@SerializedName("percentage")
@Expose
var  percentage :Float,
@SerializedName("fuelType")
@Expose
var fuelType : String ,
@SerializedName("volume")
@Expose
var volume :String ,
@SerializedName("transactionsFuelVolume")
@Expose
var transactionsFuelVolume :String,
@SerializedName("waterlevel")
@Expose
var waterlevel : String ,
@SerializedName("tanklevel")
@Expose
var tanklevel :String ,
@SerializedName("ullage")
@Expose
var ullage: String,
@SerializedName("temperature")
@Expose
var temperature : String ,
@SerializedName("currentFuelVolume")
@Expose
var currentFuelVolume :String,
@SerializedName("lastRead")
@Expose
var lastRead :Int ,
)
