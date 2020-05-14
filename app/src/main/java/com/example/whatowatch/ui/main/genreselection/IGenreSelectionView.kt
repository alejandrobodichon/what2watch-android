package com.example.whatowatch.ui.main.genreselection

import com.example.whatowatch.model.RecomendationModel


interface IGenreSelectionView {

    fun showError(message: String)
    fun hideProgressBar()
    fun showProgressBar()
}
