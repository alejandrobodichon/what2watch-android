package com.example.whatowatch.ui.main.contentselection

import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.whatowatch.R
import com.example.whatowatch.model.EmotionsModel
import com.example.whatowatch.model.GenreModel
import com.example.whatowatch.shareable.GenreAdapter
import com.example.whatowatch.shareable.WhatToWhatchFragment
import com.example.whatowatch.ui.main.MainActivity
import com.example.whatowatch.ui.main.emotions.EmotionsFragment
import com.example.whatowatch.ui.main.genreselection.GenreSelectionFragment
import com.example.whatowatch.ui.main.genreselection.GenreSelectionPresenter
import jp.wasabeef.glide.transformations.BlurTransformation
import kotlinx.android.synthetic.main.genre_selection_fragment.*
import kotlinx.android.synthetic.main.progress_bar.*
import javax.inject.Inject


class ContentSelectionFragment @Inject constructor(val fromLogin: Boolean): WhatToWhatchFragment<GenreSelectionPresenter>(), IContentSelectionView {


    override fun layout(): Int = R.layout.genre_selection_fragment

    override fun init() {
        setToolbarData(ContextCompat.getDrawable(requireContext(),R.drawable.ic_hamburger),"Content")

        Glide.with(requireContext()).load(R.mipmap.astrowalk)
            .apply(RequestOptions.bitmapTransform(BlurTransformation(22)))
            .into(requireView().findViewById(R.id.ivBackground))

        val genreItems = listOf(GenreModel("serie"),GenreModel("movie"))
        rvGenreSelection.layoutManager = LinearLayoutManager(requireContext())
        rvGenreSelection.adapter = GenreAdapter(requireContext()).also {
            it.submitList(genreItems)
        }

        btContinue.setOnClickListener {
            (rvGenreSelection.adapter as GenreAdapter).getChecked()?.let {
                if(fromLogin)
                    (requireActivity()as MainActivity).replaceFragment( EmotionsFragment(it),R.id.vBaseContent,true,
                        "Genre",false)
                    else
                (requireActivity()as MainActivity).replaceFragment( GenreSelectionFragment(it),R.id.vBaseContent,true,
                    "Genre",false)
            } ?: run {
                showError("You must choose one option.")
            }
        }



        btContinue.text = "Continue"

    }

    override fun showError(message: String) {
        showSnackBar(message)
    }

    override fun hideProgressBar(){
        clProgressBar.visibility = View.GONE
    }

    override fun showProgressBar(){
        clProgressBar.visibility = View.VISIBLE
    }

    companion object{

    }



}