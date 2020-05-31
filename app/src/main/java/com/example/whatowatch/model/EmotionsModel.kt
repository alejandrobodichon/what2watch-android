package com.example.whatowatch.model

import java.io.Serializable

data class EmotionsModel (
    val anger: String,
    val disgust: String,
    val sad: String,
    val happy: String,
    val surprise: String,
    val fear: String
) : Serializable{
    override fun toString(): String {
        return "anger:$anger,disgust:$disgust,sad:$sad,happy:$happy,surprise:$surprise,fear:$fear"
    }
}