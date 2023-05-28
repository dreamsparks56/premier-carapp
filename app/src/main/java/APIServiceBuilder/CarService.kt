package APIServiceBuilder

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface CarService {

    @GET("cars")
    fun getCarsList(
        @Header("X-Api-Key") apiKey: String,
        @Query("limit") limit: Int = 50,
    ): Call<CarsResponse>

    @GET("cars")
    fun getCarsByFuel(
        @Header("X-Api-Key") apiKey: String,
        @Query("fuel_type") fuelType: String,
        @Query("limit") limit: Int = 50,
    ): Call<CarsResponse>

    @GET("cars")
    fun getCarsByManufacturer(
        @Header("X-Api-Key") apiKey: String,
        @Query("make") manufacturer: String,
        @Query("limit") limit: Int = 50,
    ): Call<CarsResponse>

    @GET("cars")
    fun getCarsByModel(
        @Header("X-Api-Key") apiKey: String,
        @Query("model") model: String,
        @Query("limit") limit: Int = 50,
    ): Call<CarsResponse>
}