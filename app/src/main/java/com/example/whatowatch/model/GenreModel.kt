package com.example.whatowatch.model

import java.io.Serializable

data class GenreModel(val name: String?,
                      val clicked: Boolean = false)
    :Serializable
