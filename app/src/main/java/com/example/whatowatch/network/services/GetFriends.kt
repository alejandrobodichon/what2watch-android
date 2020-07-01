package com.example.whatowatch.network.services


import com.example.whatowatch.model.UserModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GetFriends {
    @GET("/users/{userId}/friends")
    fun getFriends(@Path("userId") userId: String):
            Call<List<UserModel>>
}