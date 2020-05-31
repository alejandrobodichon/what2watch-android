package com.example.whatowatch.ui.main.register

import ar.com.wolox.wolmo.core.presenter.BasePresenter
import ar.com.wolox.wolmo.networking.retrofit.RetrofitServices
import com.example.geopagos.network.callback.AuthCallback
import com.example.whatowatch.model.UserModel
import com.example.whatowatch.network.services.PostUser
import com.example.whatowatch.utils.SharedPreferencesUtils
import okhttp3.ResponseBody

import javax.inject.Inject

class RegisterPresenter @Inject constructor(private val retrofitServices: RetrofitServices) : BasePresenter<IRegisterView>() {
    @Inject
    internal lateinit var sharedPreferencesUtils: SharedPreferencesUtils

    fun register(user: UserModel){
        view?.showProgressBar()
        retrofitServices.getService(PostUser::class.java).postUser(user).enqueue(
            object : AuthCallback<UserModel>(this) {
                override fun onResponseSuccessful(response: UserModel?) {
                    view?.goBack()
                    view?.hideProgressBar()
                }

                override fun onResponseFailed(responseBody: ResponseBody?, code: Int) {
                    view?.showError("This username already exists. Please try another.")
                    //view?.hideProgressBar()
                }

                override fun onCallFailure(t: Throwable) {
                    view?.showError("Connection error, please try again.")
                    view?.hideProgressBar()
                }

            })
    }



}
