package ar.edu.ort.tp3.parcialtp3ort.APIServiceBuilder

import ar.edu.ort.tp3.parcialtp3ort.models.CarResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface CarService {

        @Headers("X-Api-Key:2fecMa9Ih8H14zWmhVlyKg==8Hy7KtF4A80Zsey6")
        @GET("cars?limit=50&fuel_type=electricity")

        fun getCarList(): Call<List<CarResponse>>


}