package com.fueltrac.model.entities

import com.google.gson.annotations.SerializedName

data class FinishTransactionRes(
    @SerializedName("Status Code")
    var statusCode: String ,
    @SerializedName("Reason Phrase")
    var reasonPhrase: String ,
)
