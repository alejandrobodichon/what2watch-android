package com.example.whatowatch.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class MainModel(@SerializedName("temp") val temp: String,
                     @SerializedName("temp_min") val minTemp: String,
                     @SerializedName("temp_max") val maxTemp: String
) : Serializable