package com.fueltrac.model.network

enum class ApiBase(baseurl: String) {
    BASE_URL("http://168.187.233.106:3223/api/FuelTrack/");

    var baseUrl = ""

    override fun toString(): String {
        return baseUrl
    }

    init {
        baseUrl = baseurl
    }
}
