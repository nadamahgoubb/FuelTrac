package com.fueltrac.model.entities

import com.fueltrac.utilities.user_Token


data class FilterDataRequest (
    var tankId :String,
    var startDate :String,
    var endDate :String,
    var token: String = user_Token

)
