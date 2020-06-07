package com.example.whatowatch.network.services


import com.example.whatowatch.model.RecomendationModel
import com.example.whatowatch.model.UserModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GetUser {
    @GET("/users")
    fun getUser(@Query("search",encoded = true) filter: String):
            Call<List<UserModel>>
}