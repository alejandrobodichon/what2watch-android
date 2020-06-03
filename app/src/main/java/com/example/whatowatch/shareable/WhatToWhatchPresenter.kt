package com.example.whatowatch.shareable

import ar.com.wolox.wolmo.core.presenter.BasePresenter
import ar.com.wolox.wolmo.networking.retrofit.RetrofitServices
import com.example.whatowatch.utils.SharedPreferencesUtils

import javax.inject.Inject

class WhatToWhatchPresenter @Inject constructor(private val retrofitServices: RetrofitServices) : BasePresenter<IWhatToWhatchView>() {
    @Inject
    internal lateinit var sharedPreferencesUtils: SharedPreferencesUtils

}
