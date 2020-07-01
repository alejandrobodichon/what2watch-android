package com.example.whatowatch.network.services


import com.example.whatowatch.model.RecomendationModel
import com.example.whatowatch.model.UserModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GetFriendSuggestions {
    @GET("/users/{userId}/friend-suggestions")
    fun getFriendSuggestions(@Path("userId") userId: String):
            Call<List<UserModel>>
}