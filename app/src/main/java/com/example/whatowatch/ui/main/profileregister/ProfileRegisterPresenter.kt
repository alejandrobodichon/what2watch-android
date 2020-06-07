package com.example.whatowatch.ui.main.profileregister

import ar.com.wolox.wolmo.core.presenter.BasePresenter
import ar.com.wolox.wolmo.networking.retrofit.RetrofitServices
import com.example.whatowatch.ui.main.login.ILoginView
import com.example.whatowatch.utils.SharedPreferencesUtils

import javax.inject.Inject

class ProfileRegisterPresenter @Inject constructor(private val retrofitServices: RetrofitServices) : BasePresenter<ILoginView>() {
    @Inject
    internal lateinit var sharedPreferencesUtils: SharedPreferencesUtils





}
