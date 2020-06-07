package com.example.whatowatch.model

data class ToolbarItem(
    val itemTitle: String,
    val icon: Int,
    var destination: Int = 0
)