package com.example.whatowatch.ui.main.cityselection

import ar.com.wolox.wolmo.core.presenter.BasePresenter
import ar.com.wolox.wolmo.networking.retrofit.RetrofitServices
import com.example.whatowatch.ui.main.contentselection.IContentSelectionView
import com.example.whatowatch.utils.SharedPreferencesUtils

import javax.inject.Inject

class ContentSelectionPresenter @Inject constructor(private val retrofitServices: RetrofitServices) : BasePresenter<IContentSelectionView>() {
    @Inject
    internal lateinit var sharedPreferencesUtils: SharedPreferencesUtils


}
