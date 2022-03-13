package com.fueltrac.model.entities

import com.google.gson.annotations.SerializedName

data class MappedListTankMeasures(

    @SerializedName("OdataCount")
     var odataCount: Int? = null,
    @SerializedName("value")
    val value: List<Value>? = null
)
