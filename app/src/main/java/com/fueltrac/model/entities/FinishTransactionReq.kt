package com.fueltrac.model.entities

data class FinishTransactionReq(
    var PlateNumber :String ,
    var DriveNumber :String ,
    var quantity :String ,
    var StartDateTime :String ,
    var pumpNumber :String ,
    var stationId :String ,
    var token :String
    )
