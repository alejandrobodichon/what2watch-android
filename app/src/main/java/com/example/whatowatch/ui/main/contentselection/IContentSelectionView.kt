package com.example.whatowatch.ui.main.contentselection

import com.example.whatowatch.model.RecomendationModel


interface IContentSelectionView {

    fun showError(message: String)
    fun hideProgressBar()
    fun showProgressBar()
}
