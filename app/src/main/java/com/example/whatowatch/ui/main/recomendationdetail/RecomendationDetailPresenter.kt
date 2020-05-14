package com.example.whatowatch.ui.main.recomendationdetail

import ar.com.wolox.wolmo.core.presenter.BasePresenter
import ar.com.wolox.wolmo.networking.retrofit.RetrofitServices
import com.example.geopagos.network.callback.AuthCallback
import com.example.whatowatch.model.RecomendationModel
import com.example.whatowatch.network.services.GetRecomendations
import com.example.whatowatch.ui.main.genreselection.IGenreSelectionView
import com.example.whatowatch.utils.SharedPreferencesUtils
import okhttp3.ResponseBody

import javax.inject.Inject

class RecomendationDetailPresenter @Inject constructor(private val retrofitServices: RetrofitServices) : BasePresenter<IRecomendationDetailView>() {
    @Inject
    internal lateinit var sharedPreferencesUtils: SharedPreferencesUtils

    fun getRecomendations(){
        view?.showProgressBar()
        retrofitServices.getService(GetRecomendations::class.java).getForecast(
        ).enqueue(
            object : AuthCallback<List<RecomendationModel>>(this) {
                override fun onResponseSuccessful(response: List<RecomendationModel>?) {
                    view?.renderRecomendation(response)
                    view?.hideProgressBar()
                }

                override fun onResponseFailed(responseBody: ResponseBody?, code: Int) {
                    view?.showError("Problema de conexión. Reintente nuevamente.")
                    view?.hideProgressBar()
                    view?.goBack()
                }

                override fun onCallFailure(t: Throwable) {
                    view?.showError("Problema de conexión. Reintente nuevamente.")
                    view?.hideProgressBar()
                    view?.goBack()
                }

            })

    }

}
