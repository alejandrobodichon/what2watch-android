package com.example.whatowatch.network.services


import com.example.whatowatch.model.RecomendationModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface PostUser {
    @POST("/users")
    fun postUser(@Body filter: String):
            Call<List<RecomendationModel>>
}