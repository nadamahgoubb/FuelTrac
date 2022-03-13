package com.fueltrac.model.entities

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class GeneralResponse<T> {


    @SerializedName("Status")
    @Expose
    private var status: Boolean? = null

    @SerializedName("Message")
    @Expose
    private var message: String? = null

    @SerializedName("Data")
    @Expose
    private var data: T? = null


    fun getStatus(): Boolean? {
        return status
    }

    fun setStatus(status: Boolean?) {
        this.status = status
    }

    fun getMessage(): String? {
        return message
    }

    fun setMessage(message: String?) {
        this.message = message
    }

    fun getData(): T? {
        return data
    }

    fun setData(data: T?) {
        this.data = data
    }


}