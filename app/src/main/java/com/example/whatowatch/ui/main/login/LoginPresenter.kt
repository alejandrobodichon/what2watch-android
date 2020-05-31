package com.example.whatowatch.ui.main.cityselection

import ar.com.wolox.wolmo.core.presenter.BasePresenter
import ar.com.wolox.wolmo.networking.retrofit.RetrofitServices
import com.example.geopagos.network.callback.AuthCallback
import com.example.whatowatch.model.RecomendationModel
import com.example.whatowatch.network.services.GetUser
import com.example.whatowatch.ui.main.login.ILoginView
import com.example.whatowatch.utils.SharedPreferencesUtils
import com.example.whatowatch.utils.Utils.Companion.API_KEY
import okhttp3.ResponseBody

import javax.inject.Inject

class LoginPresenter @Inject constructor(private val retrofitServices: RetrofitServices) : BasePresenter<ILoginView>() {
    @Inject
    internal lateinit var sharedPreferencesUtils: SharedPreferencesUtils

    fun login(user: String, pass: String){
        view?.showProgressBar()
        retrofitServices.getService(GetUser::class.java).getUser("username:$user,pass:$pass").enqueue(
            object : AuthCallback<List<RecomendationModel>>(this) {
                override fun onResponseSuccessful(response: List<RecomendationModel>?) {
                    view?.loginSuccessful()
                    view?.hideProgressBar()
                }

                override fun onResponseFailed(responseBody: ResponseBody?, code: Int) {
                    view?.showError("The username or password is incorrect.")
                    //view?.hideProgressBar()
                }

                override fun onCallFailure(t: Throwable) {
                    view?.showError("Connection error, please try again.")
                    view?.hideProgressBar()
                }

            })
    }





}
