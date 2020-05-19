package com.example.whatowatch.ui.main.genreselection

import ar.com.wolox.wolmo.core.presenter.BasePresenter
import ar.com.wolox.wolmo.networking.retrofit.RetrofitServices
import com.example.whatowatch.ui.main.genreselection.IGenreSelectionView
import com.example.whatowatch.utils.SharedPreferencesUtils

import javax.inject.Inject

class GenreSelectionPresenter @Inject constructor(private val retrofitServices: RetrofitServices) : BasePresenter<IGenreSelectionView>() {
    @Inject
    internal lateinit var sharedPreferencesUtils: SharedPreferencesUtils


}
