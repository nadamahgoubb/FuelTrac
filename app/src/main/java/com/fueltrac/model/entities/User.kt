package com.fueltrac.model.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = "User")
class User {


    constructor(userId: Int?, token: String?, userType: String?) {
        this.userId = userId
        this.token = token
        this.userType = userType
    }

    @SerializedName("userId")
    @PrimaryKey
    var userId: Int? = null

    @SerializedName("token")
    var token: String? = null

    @SerializedName("userType")
    var userType: String? = null



}