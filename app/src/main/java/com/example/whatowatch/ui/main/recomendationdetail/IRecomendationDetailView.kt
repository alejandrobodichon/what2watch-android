package com.example.whatowatch.ui.main.recomendationdetail

import com.example.whatowatch.model.RecomendationModel


interface IRecomendationDetailView {

    fun renderRecomendation(recomendationModel: List<RecomendationModel>?)
    fun showError(message: String)
    fun goBack()
    fun hideProgressBar()
    fun showProgressBar()
}
