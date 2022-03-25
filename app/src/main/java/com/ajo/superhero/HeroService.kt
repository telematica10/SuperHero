package com.ajo.superhero

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface HeroService {
    @GET("/v1/public/comics?")
    fun getCurrentHeroData( @Query("apikey") app_id: String): Call<HeroResponse>

}