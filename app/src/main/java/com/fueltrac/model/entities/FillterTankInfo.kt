package com.fueltrac.model.entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class FillterTankInfo (


    @SerializedName("color") 
     var color: Int? = null
    ,@SerializedName("tankId")

 var tankId: Int? = null

,@SerializedName("name")

 var name: String? = null

,@SerializedName("percentage")

 var percentage: Double? = null

,@SerializedName("fuelType")

 var fuelType: String? = null

,@SerializedName("tankVloumeCapacity")

 var tankVloumeCapacity: Int? = null

,@SerializedName("transactionsFuelVolume")

 var transactionsFuelVolume: Int? = null

,@SerializedName("tankDeliveriesFuelVolume")

 var tankDeliveriesFuelVolume: Int? = null

,@SerializedName("tankWithdrawalsFuelVolume")

 var tankWithdrawalsFuelVolume: Int? = null

,@SerializedName("currentFuelVolume")

 var currentFuelVolume: Int? = null

,@SerializedName("mappedListTankMeasures")

 var mappedListTankMeasures: MappedListTankMeasures? = null
    
        )
