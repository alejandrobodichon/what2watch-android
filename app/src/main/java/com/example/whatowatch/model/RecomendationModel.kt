package com.example.whatowatch.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class RecomendationModel(@SerializedName("title") val title: String,
                              @SerializedName("plot") val description: String,
                              @SerializedName("actors") val mainCast: String,
                              @SerializedName("director") val director: String?,
                              @SerializedName("year") val year: String,
                              @SerializedName("type") val type: String,
                              @SerializedName("genre") val genre: String,
                              @SerializedName("poster") val cover_url: String?,
                              @SerializedName("netflix") val netflix: String?,
                              @SerializedName("amazon") val amazon: String?
) : Serializable