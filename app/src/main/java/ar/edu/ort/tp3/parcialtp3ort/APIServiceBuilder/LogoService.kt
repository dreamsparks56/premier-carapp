package ar.edu.ort.tp3.parcialtp3ort.APIServiceBuilder

import ar.edu.ort.tp3.parcialtp3ort.Models.LogoResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface LogoService {

    @GET("logo")
    fun getLogoByName(
        @Query("name") name: String,
    ): Call<List<LogoResponse>>

    @GET("logo")
    fun getLogoByTicker(
        @Query("ticker") ticker: String,
    ): Call<List<LogoResponse>>

}