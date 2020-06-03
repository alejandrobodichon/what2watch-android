package com.example.whatowatch.ui.main.recomendationdetail

import com.example.whatowatch.model.RecomendationModel
import com.example.whatowatch.shareable.IWhatToWhatchView


interface IRecomendationDetailView: IWhatToWhatchView{

    fun renderRecomendation(recomendationModel: List<RecomendationModel>?)
    fun goBack()

}
