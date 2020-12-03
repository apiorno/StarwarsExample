package com.example.starwarsexample.retrofit

import com.example.starwarsexample.pojos.Film
import com.example.starwarsexample.pojos.StarWars
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url


interface APIInterface {

    @GET("people/?")
    fun getPeople(@Query("format") format: String?): Call<StarWars?>?

    @GET
    fun getFilmData(@Url url: String?, @Query("format") format: String?): Call<Film?>?
}