package com.example.whatowatch.network.services


import com.example.whatowatch.model.RecomendationModel
import com.example.whatowatch.model.UserModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface PostUser {
    @POST("/users")
    fun postUser(@Body user: UserModel):
            Call<UserModel>
}