package com.example.whatowatch.ui.main.genreselection

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import ar.com.wolox.wolmo.core.util.ToastFactory
import com.example.whatowatch.R
import com.example.whatowatch.model.GenreModel
import com.example.whatowatch.shareable.GenreAdapter
import com.example.whatowatch.ui.main.MainActivity
import com.example.whatowatch.ui.main.cityselection.GenreSelectionPresenter
import com.example.whatowatch.utils.SharedPreferencesUtils
import kotlinx.android.synthetic.main.genre_selection_fragment.*
import kotlinx.android.synthetic.main.progress_bar.*
import javax.inject.Inject


class GenreSelectionFragment @Inject constructor(): WolmoFragment<GenreSelectionPresenter>(), IGenreSelectionView {

    @Inject internal lateinit var sharedPreferencesUtils: SharedPreferencesUtils
    @Inject internal lateinit var toastFactory: ToastFactory

    override fun layout(): Int = R.layout.genre_selection_fragment

    override fun init() {
        val genreItems = listOf(GenreModel("Drama"),GenreModel("Ciencia ficción"),GenreModel("Comedia"),GenreModel("Romance"),GenreModel("Thriller"),GenreModel("Acción"),GenreModel("Horror"),GenreModel("Fantasía"))
        rvGenreSelection.layoutManager = LinearLayoutManager(requireContext())
        rvGenreSelection.adapter = GenreAdapter(requireContext()).also {
            it.submitList(genreItems)
        }

    }


    override fun showError(message: String) {
        (requireActivity() as MainActivity).showSnackBar(view!!.rootView,message)
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