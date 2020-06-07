package com.example.whatowatch.ui.main.login

import ar.com.wolox.wolmo.core.presenter.BasePresenter
import ar.com.wolox.wolmo.networking.retrofit.RetrofitServices
import com.example.whatowatch.model.UserModel
import com.example.whatowatch.network.callback.AuthCallback
import com.example.whatowatch.network.services.GetUser
import com.example.whatowatch.utils.SharedPreferencesUtils
import okhttp3.ResponseBody

import javax.inject.Inject

class LoginPresenter @Inject constructor(private val retrofitServices: RetrofitServices) : BasePresenter<ILoginView>() {
    @Inject
    internal lateinit var sharedPreferencesUtils: SharedPreferencesUtils

    fun login(user: String, pass: String){
        view?.showProgressBar()
        retrofitServices.getService(GetUser::class.java).getUser("username:$user,pass:$pass").enqueue(
            object : AuthCallback<List<UserModel>>(this) {
                override fun onResponseSuccessful(response: List<UserModel>?) {
                    sharedPreferencesUtils.user = response!![0]
                    view?.loginSuccessful()
                    view?.hideProgressBar()
                }

                override fun onResponseFailed(responseBody: ResponseBody?, code: Int) {
                    view?.showError("The username or password is incorrect.")
                    view?.hideProgressBar()
                }

                override fun onCallFailure(t: Throwable) {
                    view?.showError("Connection error, please try again.")
                    view?.hideProgressBar()
                }

            })
    }





}
