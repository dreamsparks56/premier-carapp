package ar.edu.ort.tp3.parcialtp3ort.Models

import com.google.gson.annotations.SerializedName

data class CarResponse(
    @SerializedName("city_mpg")
    val consumoCiudad: Double,
    @SerializedName("class")
    val descripcionAuto: String,
    @SerializedName("combination_mpg")
    val consumoCombinado: Int,
    @SerializedName("cylinders")
    val motor: Int,
    @SerializedName("displacement")
    val cilindrada: Double,
    @SerializedName("drive")
    val tipoConduccion: String,
    @SerializedName("fuel_type")
    val combustible: String,
    @SerializedName("highway_mpg")
    val consumoAutopista: Double,
    @SerializedName("make")
    val marca: String,
    @SerializedName("model")
    val modelo: String,
    @SerializedName("transmission")
    val caja: String,
    @SerializedName("year")
    val ano: String,
    var image: String? = null,
)