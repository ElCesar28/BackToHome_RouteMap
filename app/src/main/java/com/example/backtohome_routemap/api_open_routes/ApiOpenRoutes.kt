package com.example.backtohome_routemap.api_open_routes

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://api.openrouteservice.org"


private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

private val retrofit =
    Retrofit.Builder().addConverterFactory(MoshiConverterFactory.create(moshi)).baseUrl(BASE_URL)
        .build()


interface getRoute {
    @GET("v2/directions/driving-car")
    suspend fun getPoints(
        @Query("api_key") apiKey: String,
        @Query("start") inicio: String,
        @Query("end") final: String
    ): Coordenadas
}


object openRoutes {
    val retrofitService: getRoute by lazy {
        retrofit.create(getRoute::class.java)
    }
}