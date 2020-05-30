package com.example.whatowatch.ui.main.cityselection

import ar.com.wolox.wolmo.core.presenter.BasePresenter
import ar.com.wolox.wolmo.networking.retrofit.RetrofitServices
import com.example.geopagos.network.callback.AuthCallback
import com.example.whatowatch.ui.main.login.ILoginView
import com.example.whatowatch.utils.SharedPreferencesUtils
import com.example.whatowatch.utils.Utils.Companion.API_KEY
import okhttp3.ResponseBody

import javax.inject.Inject

class GuestPresenter @Inject constructor(private val retrofitServices: RetrofitServices) : BasePresenter<ILoginView>() {
    @Inject
    internal lateinit var sharedPreferencesUtils: SharedPreferencesUtils





}
