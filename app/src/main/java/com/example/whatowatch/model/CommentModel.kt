package com.example.whatowatch.model

import java.io.Serializable

data class CommentModel (
    var id: Int?,
    var userName: String?,
    var detail: String?
) : Serializable