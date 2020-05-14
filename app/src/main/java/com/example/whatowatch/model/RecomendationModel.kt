package com.example.whatowatch.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class RecomendationModel(@SerializedName("title") val title: String,
                              @SerializedName("description") val description: String,
                              @SerializedName("main_cast") val mainCast: String,
                              @SerializedName("director") val director: String,
                              @SerializedName("year") val year: String,
                              @SerializedName("type") val type: String,
                              @SerializedName("genre") val genre: String,
                              @SerializedName("cover_url") val cover_url: String
) : Serializable