package com.example.whatowatch.ui.main.contentselection

import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.whatowatch.R
import com.example.whatowatch.model.GenreModel
import com.example.whatowatch.shareable.GenreAdapter
import com.example.whatowatch.shareable.WhatToWhatchFragment
import com.example.whatowatch.ui.main.MainActivity
import com.example.whatowatch.ui.main.emoticons.EmoticonsFragment
import com.example.whatowatch.ui.main.genreselection.GenreSelectionFragment
import com.example.whatowatch.ui.main.genreselection.GenreSelectionPresenter
import kotlinx.android.synthetic.main.genre_selection_fragment.*
import javax.inject.Inject


class ContentSelectionFragment @Inject constructor(private val fromLogin: Boolean) :
    WhatToWhatchFragment<GenreSelectionPresenter>(), IContentSelectionView {


    override fun layout(): Int = R.layout.genre_selection_fragment

    override fun init() {
        setToolbarData(
            ContextCompat.getDrawable(requireContext(), R.drawable.ic_hamburger),
            "Content"
        )
        setImageBackground()

        val genreItems = listOf(GenreModel("serie"), GenreModel("movie"))
        rvGenreSelection.layoutManager = LinearLayoutManager(requireContext())
        rvGenreSelection.adapter = GenreAdapter(requireContext()).also {
            it.submitList(genreItems)
        }

        btContinue.setOnClickListener {
            (rvGenreSelection.adapter as GenreAdapter).getChecked()?.let {
                if (fromLogin)
                    (requireActivity() as MainActivity).manageFragmentsSlideAnimation(
                        EmoticonsFragment(it),
                        null
                    )
                else
                    (requireActivity() as MainActivity).manageFragmentsSlideAnimation(
                        GenreSelectionFragment(it),
                        null
                    )
            } ?: run {
                showError("You must choose one option.")
            }
        }
        
        btContinue.text = "Continue"

    }

    override fun showError(message: String) {
        showSnackBar(message)
    }



}