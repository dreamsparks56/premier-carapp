package ar.edu.ort.tp3.parcialtp3ort.Models

import com.google.gson.annotations.SerializedName


data class LogoResponse(
    @SerializedName("name")
    val nombre: String,
    @SerializedName("ticker")
    val abreviatura: String,
    @SerializedName("image")
    val imagenURL: String,
)
