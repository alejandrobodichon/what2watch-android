package com.example.whatowatch.ui.main.recomendationdetail

import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.text.Spannable
import android.text.SpannableString
import android.text.style.StyleSpan
import android.view.View
import androidx.core.content.ContextCompat
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import com.bumptech.glide.Glide
import com.example.whatowatch.R
import com.example.whatowatch.model.RecomendationModel
import com.example.whatowatch.ui.main.MainActivity
import com.example.whatowatch.utils.SharedPreferencesUtils
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.detail_fragment.*
import kotlinx.android.synthetic.main.progress_bar.*
import javax.inject.Inject


class RecomendationDetailFragment @Inject constructor(): WolmoFragment<RecomendationDetailPresenter>(), IRecomendationDetailView {

    @Inject internal lateinit var sharedPreferencesUtils: SharedPreferencesUtils
    private lateinit var recomendationList: List<RecomendationModel>
    private var position: Int = 0

    override fun layout(): Int = R.layout.detail_fragment

    override fun init() {
        setToolbarData(ContextCompat.getDrawable(requireContext(),R.drawable.ic_hamburger),sharedPreferencesUtils.name.toString())

        presenter.getRecomendations()
        btAnother.setOnClickListener {
            if(position+1<=recomendationList.size-1)
                position++
            else{
                position=0
            }
            renderLayout(recomendationList[position])
        }

        btRestart.setOnClickListener {
            goBack()
        }
    }

    override fun renderRecomendation(recomendationModelList: List<RecomendationModel>?) {
        recomendationList = recomendationModelList!!
        renderLayout(recomendationModelList[position])
    }

    private fun renderLayout(recomendationModel: RecomendationModel){
        Glide.with(requireContext())
            .asBitmap()
            .load(recomendationModel.cover_url)
            .into(ivImage)

        tvTitle.text = recomendationModel.title
        tvDetail.text = recomendationModel.description
        tvYear.text = SpannableString( getString(R.string.detail_year,recomendationModel.year)).apply {
            setSpan(StyleSpan(Typeface.BOLD), 5,9 , Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
        tvGenre.text = SpannableString( getString(R.string.detail_genre,recomendationModel.genre)).apply {
            setSpan(StyleSpan(Typeface.BOLD), 8,8 + recomendationModel.genre.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
        tvMainCast.text = SpannableString( getString(R.string.detail_main_cast,recomendationModel.mainCast)).apply {
            setSpan(StyleSpan(Typeface.BOLD), 19,19+ recomendationModel.mainCast.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
        tvDirector.text = SpannableString( getString(R.string.detail_director,recomendationModel.director)).apply {
            setSpan(StyleSpan(Typeface.BOLD), 10, 10 +recomendationModel.director.length , Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        }

    }


    override fun showError(message: String) {
        (requireActivity() as MainActivity).showSnackBar(view!!.rootView,message)
    }

    override fun goBack() {
        (requireActivity() as MainActivity).backToFragmentPosition(0)
    }

    override fun hideProgressBar(){
        clProgressBar.visibility = View.GONE
    }

    override fun showProgressBar(){
        clProgressBar.visibility = View.VISIBLE
    }

    companion object{

    }

    fun setToolbarData(navigationIcon: Drawable?, title: String) {
        requireActivity().vToolbar.apply {
            this@apply.navigationIcon = navigationIcon
            this.title = title
            this.visibility = View.VISIBLE
        }
    }
}