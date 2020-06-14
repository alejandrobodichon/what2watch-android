package com.example.whatowatch.ui.main.genreselection

import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.whatowatch.R
import com.example.whatowatch.model.GenreModel
import com.example.whatowatch.shareable.EmoticonsAdapter
import com.example.whatowatch.shareable.GenreAdapter
import com.example.whatowatch.shareable.WhatToWhatchFragment
import com.example.whatowatch.ui.main.MainActivity
import com.example.whatowatch.ui.main.recomendationdetail.RecomendationDetailFragment
import kotlinx.android.synthetic.main.genre_selection_fragment.*
import javax.inject.Inject


class GenreSelectionFragment @Inject constructor(val content: String) :
    WhatToWhatchFragment<GenreSelectionPresenter>(), IGenreSelectionView {


    override fun layout(): Int = R.layout.genre_selection_fragment

    override fun init() {
        setToolbarData(
            ContextCompat.getDrawable(requireContext(), R.drawable.ic_hamburger),
            "Genre"
        )
        setImageBackground()

        val genreItems = listOf(
            GenreModel("Comedy"),
            GenreModel("Horror"),
            GenreModel("Romance"),
            GenreModel("Action"),
            GenreModel("Thriller"),
            GenreModel("Drama"),
            GenreModel("Animation"),
            GenreModel("Fantasy")
        )
        rvGenreSelection.layoutManager = LinearLayoutManager(requireContext())
        rvGenreSelection.adapter = GenreAdapter(requireContext()).also {
            it.submitList(genreItems)
        }

        btContinue.setOnClickListener {
            (rvGenreSelection.adapter as GenreAdapter).getChecked()?.let {
                (requireActivity() as MainActivity).manageFragmentsSlideAnimation(RecomendationDetailFragment(content,it,null),null)
            } ?: run {
                showError("You must choose one option.")
            }

        }

    }

}