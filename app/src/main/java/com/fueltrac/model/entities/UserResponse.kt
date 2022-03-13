package com.fueltrac.model.entities

import androidx.room.PrimaryKey
import com.fueltrac.model.entities.StationsResponse
import com.google.gson.annotations.SerializedName


class UserResponse {


    @SerializedName("userId")
    @PrimaryKey
    var userId: Int? = null

    @SerializedName("token")
    var token: String? = null

    @SerializedName("userType")
    var userType: String? = null

    @SerializedName("stations")
    private var Stations: List<StationsResponse>? = null

    fun getStations(): List<StationsResponse>? {
        return Stations
    }

    fun setStations(Stations: List<StationsResponse>?) {
        this.Stations = Stations
    }

    override fun toString(): String {
        return "User(token=$token, userId=$userId, userType=$userType, stations=$Stations)"
    }

}