package com.example.whatowatch.network.callback

import ar.com.wolox.wolmo.core.presenter.BasePresenter
import ar.com.wolox.wolmo.networking.retrofit.callback.NetworkCallback
import retrofit2.Call
import retrofit2.Response

abstract class AuthCallback<T> constructor(
    private val presenter: BasePresenter<*>
) : NetworkCallback<T>() {

    override fun onResponse(call: Call<T>, response: Response<T>) {
        if (response.isSuccessful && presenter.isViewAttached()) {
            this.onResponseSuccessful(response.body())
        } else if (presenter.isViewAttached()) {
            this.onResponseFailed(response.errorBody(), response.code())
        }
    }
}
