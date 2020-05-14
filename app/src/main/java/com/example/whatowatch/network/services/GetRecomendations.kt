package com.example.whatowatch.network.services


import com.example.whatowatch.model.RecomendationModel
import retrofit2.Call
import retrofit2.http.GET

interface GetRecomendations {

    @GET("/contents")
    fun getForecast(): Call<List<RecomendationModel>>
}