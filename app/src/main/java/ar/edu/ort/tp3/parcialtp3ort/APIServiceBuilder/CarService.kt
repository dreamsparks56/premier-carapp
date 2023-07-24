package ar.edu.ort.tp3.parcialtp3ort.APIServiceBuilder

import ar.edu.ort.tp3.parcialtp3ort.Models.CarResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface CarService {

    @GET("cars")
    fun getCarsList(
        @Query("limit") limit: Int = 50,
    ): Call<List<CarResponse>>

    @GET("cars")
    fun getCarsByFuel(
        @Query("fuel_type") fuelType: String,
        @Query("limit") limit: Int = 50,
    ): Call<List<CarResponse>>

    @GET("cars")
    fun getCarsByManufacturer(
        @Query("make") manufacturer: String,
        @Query("limit") limit: Int = 25,
    ): Call<List<CarResponse>>

    @GET("cars")
    fun getCarsByModel(
        @Query("model") model: String,
        @Query("limit") limit: Int = 25,
    ): Call<List<CarResponse>>
}