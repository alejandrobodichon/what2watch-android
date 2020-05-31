package com.example.whatowatch.model

import java.io.Serializable

data class UserModel (
    val name: String,
    val surname: String,
    val username: String,
    val pass: String,
    val email: String,
    val birth_date: String
) : Serializable