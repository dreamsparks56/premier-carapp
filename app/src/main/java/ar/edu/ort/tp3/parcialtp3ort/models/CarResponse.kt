package ar.edu.ort.tp3.parcialtp3ort.models

import com.google.gson.annotations.SerializedName

data class CarResponse(
    @SerializedName(value = "class") val car_class: String,
    val drive: String,
    val fuel_type: String,
    val make: String,
    val model: String,
    val year: String
)
