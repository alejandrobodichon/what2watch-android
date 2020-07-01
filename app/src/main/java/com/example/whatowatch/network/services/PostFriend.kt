package com.example.whatowatch.network.services


import com.example.whatowatch.model.IdModel
import com.example.whatowatch.model.UserModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

interface PostFriend {
    @POST("/users/{userId}/friends")
    fun postFriend(@Body id: IdModel,
                 @Path("userId") userId: String):
            Call<List<UserModel>>
}