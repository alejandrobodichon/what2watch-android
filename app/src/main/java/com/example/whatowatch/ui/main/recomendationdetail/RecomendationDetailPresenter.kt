package com.example.whatowatch.ui.main.recomendationdetail

import ar.com.wolox.wolmo.core.presenter.BasePresenter
import ar.com.wolox.wolmo.networking.retrofit.RetrofitServices
import com.example.geopagos.network.callback.AuthCallback
import com.example.whatowatch.model.RecomendationModel
import com.example.whatowatch.network.services.GetRecommendations
import com.example.whatowatch.utils.SharedPreferencesUtils
import okhttp3.ResponseBody

import javax.inject.Inject

class RecomendationDetailPresenter @Inject constructor(private val retrofitServices: RetrofitServices) : BasePresenter<IRecomendationDetailView>() {
    @Inject
    internal lateinit var sharedPreferencesUtils: SharedPreferencesUtils

    fun getRecomendations(content: String, genre: String){
        view?.showProgressBar()
        retrofitServices.getService(GetRecommendations::class.java).getRecommendation("type:$content,genre:$genre").enqueue(
            object : AuthCallback<List<RecomendationModel>>(this) {
                override fun onResponseSuccessful(response: List<RecomendationModel>?) {
                    view?.renderRecomendation(response)
                    view?.hideProgressBar()
                }

                override fun onResponseFailed(responseBody: ResponseBody?, code: Int) {
                    view?.showError("Connection error, please try again.")
                    view?.hideProgressBar()
                    view?.goBack()
                }

                override fun onCallFailure(t: Throwable) {
                    view?.showError("Connection error, please try again.")
                    view?.hideProgressBar()
                    view?.goBack()
                }

            })

    }

}
