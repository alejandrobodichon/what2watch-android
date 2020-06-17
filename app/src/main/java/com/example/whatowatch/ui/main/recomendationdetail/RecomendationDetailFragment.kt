package com.example.whatowatch.ui.main.recomendationdetail

import android.content.Intent
import android.graphics.Typeface
import android.net.Uri
import android.text.Spannable
import android.text.SpannableString
import android.text.style.StyleSpan
import android.view.View
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.example.whatowatch.R
import com.example.whatowatch.model.EmoticonsModel
import com.example.whatowatch.model.EmotionsModel
import com.example.whatowatch.model.RecomendationModel
import com.example.whatowatch.shareable.WhatToWhatchFragment
import com.example.whatowatch.ui.main.MainActivity
import kotlinx.android.synthetic.main.detail_fragment.*
import kotlinx.android.synthetic.main.fragment_guest.*
import javax.inject.Inject


class RecomendationDetailFragment @Inject constructor(val content: String?,  val emotionsList: List<EmoticonsModel>?, val genre: String?) :
    WhatToWhatchFragment<RecomendationDetailPresenter>(), IRecomendationDetailView {

    private lateinit var recommendationList: List<RecomendationModel>
    private var position: Int = 0


    override fun layout(): Int = R.layout.detail_fragment

    override fun init() {
        val name = sharedPreferencesUtils.user?.let {
            sharedPreferencesUtils.user?.name
        }?:run{
            sharedPreferencesUtils.name
        }

        setToolbarData(
            ContextCompat.getDrawable(requireContext(), R.drawable.ic_hamburger),
            name!!
        )

        presenter.getRecomendations(content, emotionsList, genre)
        btAnother.setOnClickListener {
            if (position + 1 <= recommendationList.size - 1)
                position++
            else {
                position = 0
            }
            renderLayout(recommendationList[position])
            if (ivDislike.isSelected)
                ivDislike.isSelected = false
            if (ivLike.isSelected)
                ivLike.isSelected = false
        }

        btRestart.setOnClickListener {
            (requireActivity() as MainActivity).backToFragmentPosition(2)
        }

        btComment.setOnClickListener {
            btComment.visibility = View.GONE
            btComment2.visibility = View.VISIBLE
            etCommment.visibility = View.VISIBLE
        }

        ivLike.setOnClickListener {
            ivLike.isSelected = !ivLike.isSelected
            if (ivDislike.isSelected)
                ivDislike.isSelected = false
        }

        ivDislike.setOnClickListener {
            ivDislike.isSelected = !ivDislike.isSelected
            if (ivLike.isSelected)
                ivLike.isSelected = false
        }



    }

    override fun renderRecomendation(recomendationModelList: List<RecomendationModel>?) {
        recommendationList = recomendationModelList!!
        if (recomendationModelList.isEmpty()) {
            showError("No content to show.")
            goBack()
        } else
            renderLayout(recomendationModelList[position])
    }

    private fun renderLayout(recomendationModel: RecomendationModel) {
        Glide.with(requireContext())
            .asBitmap()
            .load(recomendationModel.cover_url)
            .into(ivImage)

        tvTitle.text = recomendationModel.title
        tvDetail.text = recomendationModel.description
        tvYear.text =
            SpannableString(getString(R.string.detail_year, recomendationModel.year)).apply {
                setSpan(StyleSpan(Typeface.BOLD), 6, 10, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            }
        tvGenre.text =
            SpannableString(getString(R.string.detail_genre, recomendationModel.genre)).apply {
                setSpan(
                    StyleSpan(Typeface.BOLD),
                    7,
                    7 + recomendationModel.genre?.length,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            }
        recomendationModel.mainCast?.let{
            tvMainCast.text = SpannableString(
                getString(
                    R.string.detail_main_cast,
                    recomendationModel.mainCast
                )
            ).apply {
                setSpan(
                    StyleSpan(Typeface.BOLD),
                    11,
                    11 + recomendationModel.mainCast?.length,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            }
        }

        tvDirector.text = SpannableString(
            getString(
                R.string.detail_director,
                recomendationModel.director
            )
        ).apply {
            setSpan(
                StyleSpan(Typeface.BOLD),
                10,
                10 + recomendationModel.director!!.length,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }

        recomendationModel.amazon?.let {
            if(recomendationModel.amazon == "N/A")
                ivAmazonPrime.visibility = View.GONE
            else
                ivAmazonPrime.visibility = View.VISIBLE
        }?:run{
            ivAmazonPrime.visibility = View.GONE
        }

        recomendationModel.netflix?.let {
            if(recomendationModel.netflix == "N/A")
                ivNetflix.visibility = View.GONE
            else
                ivNetflix.visibility = View.VISIBLE

        }?:run{
            ivNetflix.visibility = View.GONE
        }

        ivNetflix.setOnClickListener {
            recomendationModel.netflix?.let {
                try {
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.setClassName(
                        "com.netflix.mediaclient",
                        "com.netflix.mediaclient.ui.launch.UIWebViewActivity"
                    )
                    intent.data = Uri.parse(it)
                    startActivity(intent)
                } catch (e: Exception) { // netflix app isn't installed, send to website.
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.data = Uri.parse(it)
                    startActivity(intent)
                }
            }
        }

    }


    override fun goBack() {
        (requireActivity() as MainActivity).backToFragmentPosition(1)
    }


}