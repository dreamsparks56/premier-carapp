package ar.edu.ort.tp3.parcialtp3ort.APIServiceBuilder

import android.util.Log
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient

object APIServiceBuilder {
    private val apiKey = "L+1QgbfWdbm3sz3sL7lr/g==kDoyGb6D0ymyUi2K"
    private val BASE_URL = "https://api.api-ninjas.com/v1/"

    val httpClient = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader("X-Api-Key", apiKey)
                .build()
            Log.d("Interceptor: ", request.toString())
            chain.proceed(request)
        }
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(httpClient)
        .apply {  }
        .build()

    fun createCarService(): CarService {
        return retrofit.create(CarService::class.java)
    }
    fun createLogoService(): LogoService {
        return retrofit.create(LogoService::class.java)
    }
}