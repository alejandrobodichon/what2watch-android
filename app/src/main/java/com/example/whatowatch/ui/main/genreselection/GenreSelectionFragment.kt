package com.example.whatowatch.ui.main.genreselection

import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import ar.com.wolox.wolmo.core.util.ToastFactory
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.whatowatch.R
import com.example.whatowatch.model.GenreModel
import com.example.whatowatch.shareable.GenreAdapter
import com.example.whatowatch.shareable.WhatToWhatchFragment
import com.example.whatowatch.ui.main.MainActivity
import com.example.whatowatch.ui.main.recomendationdetail.RecomendationDetailFragment
import com.example.whatowatch.utils.SharedPreferencesUtils
import jp.wasabeef.glide.transformations.BlurTransformation
import kotlinx.android.synthetic.main.genre_selection_fragment.*
import kotlinx.android.synthetic.main.progress_bar.*
import javax.inject.Inject


class GenreSelectionFragment @Inject constructor(val content: String) :
    WhatToWhatchFragment<GenreSelectionPresenter>(), IGenreSelectionView {


    override fun layout(): Int = R.layout.genre_selection_fragment

    override fun init() {
        setToolbarData(
            ContextCompat.getDrawable(requireContext(), R.drawable.ic_hamburger),
            "Genre"
        )

        Glide.with(requireContext()).load(R.mipmap.astrowalk)
            .apply(RequestOptions.bitmapTransform(BlurTransformation(22)))
            .into(requireView().findViewById(R.id.ivBackground))

        val genreItems = listOf(
            GenreModel("drama"),
            GenreModel("suspense"),
            GenreModel("comedy"),
            GenreModel("romance"),
            GenreModel("action"),
            GenreModel("horror"),
            GenreModel("animation")
        )
        rvGenreSelection.layoutManager = LinearLayoutManager(requireContext())
        rvGenreSelection.adapter = GenreAdapter(requireContext()).also {
            it.submitList(genreItems)
        }

        btContinue.setOnClickListener {
            (rvGenreSelection.adapter as GenreAdapter).getChecked()?.let {
                (requireActivity() as MainActivity).replaceFragment(
                    RecomendationDetailFragment(content,it,null), R.id.vBaseContent, true,
                    "Recommend", false
                )
            } ?: run {
                showError("You must choose one option.")
            }

        }

    }


    override fun showError(message: String) {
        showSnackBar(message)
    }

    override fun hideProgressBar() {
        clProgressBar.visibility = View.GONE
    }

    override fun showProgressBar() {
        clProgressBar.visibility = View.VISIBLE
    }

    companion object {

    }


}