package com.fueltrac.model.entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class Searchresult {

    @SerializedName("companyNumber")
    @Expose
    private var color: String = ""

    @SerializedName("model")
    @Expose
    private var model: String = ""

    @SerializedName("type")
    @Expose
    private var type: String = ""

    @SerializedName("plateNumber")
    @Expose
    private var plateNumber: String = ""

    @SerializedName("fuelType")
    @Expose
    private var fuelType: String = ""

    @SerializedName("driver")
    @Expose
    private var driver: String = ""

    @SerializedName("notes")
    @Expose
    private var notes: String = ""

    @SerializedName("department")
    @Expose
    private var department: String = ""

    @SerializedName("maximumAllowed")
    @Expose
    private var maximumAllowed: String = ""

    @SerializedName("lastFuelDate")
    @Expose
    private var lastFuelDate: String = ""

    @SerializedName("status")
    @Expose
    private var status: Boolean  = false

    fun getColor(): String {
        return color
    }

    fun setColor(companyNumber: String) {
        this.color = companyNumber
    }

    fun getModel(): String {
        return model
    }

    fun setModel(model: String) {
        this.model = model
    }

    fun getType(): String {
        return type
    }

    fun setType(type: String) {
        this.type = type
    }

    fun getPlateNumber(): String {
        return plateNumber
    }

    fun setPlateNumber(plateNumber: String) {
        this.plateNumber = plateNumber
    }

    fun getFuelType(): String {
        return fuelType
    }

    fun setFuelType(fuelType: String) {
        this.fuelType = fuelType
    }

    fun getDriver(): String {
        return driver
    }

    fun setDriver(driver: String) {
        this.driver = driver
    }

    fun getNotes(): String {
        return notes
    }

    fun setNotes(notes: String) {
        this.notes = notes
    }

    fun getDepartment(): String {
        return department
    }

    fun setDepartment(department: String) {
        this.department = department
    }

    fun getMaximumAllowed(): String {
        return maximumAllowed
    }

    fun setMaximumAllowed(maximumAllowed: String) {
        this.maximumAllowed = maximumAllowed
    }

    fun getLastFuelDate(): String {
        return lastFuelDate
    }

    fun setLastFuelDate(lastFuelDate: String) {
        this.lastFuelDate = lastFuelDate
    }

    fun getStatus() : Boolean {
        return status
    }

    fun setStatus (status : Boolean) {
        this.status = status
    }

   

    override fun toString(): String {
        return "Searchresult(color=$color, model=$model, type=$type, plateNumber=$plateNumber, fuelType=$fuelType, driver=$driver, notes=$notes, department=$department, maximumAllowed=$maximumAllowed, lastFuelDate=$lastFuelDate, status=$status)"
    }


}