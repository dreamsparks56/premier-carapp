package APIServiceBuilder

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface LogoService {

    @GET("logo")
    fun getLogoByName(
        @Header("X-Api-Key") apiKey: String,
        @Query("name") name: String,
    ): Call<LogoResponse>

    @GET("logo")
    fun getLogoByTicker(
        @Header("X-Api-Key") apiKey: String,
        @Query("ticker") ticker: String,
    ): Call<LogoResponse>

}