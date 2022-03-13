package com.fueltrac.model.network

interface GeneralListener<T> {
    fun getApiResponse(
        status: Int, message: String?,
        tApiResponse: T
    )
}



