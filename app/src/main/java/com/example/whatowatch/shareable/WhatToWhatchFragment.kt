package com.example.whatowatch.shareable

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.FrameLayout

import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import ar.com.wolox.wolmo.core.presenter.BasePresenter
import ar.com.wolox.wolmo.core.util.ToastFactory
import com.example.whatowatch.R
import com.example.whatowatch.ui.main.MainActivity
import com.example.whatowatch.utils.SharedPreferencesUtils
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.progress_bar.*
import javax.inject.Inject

abstract class WhatToWhatchFragment<T : BasePresenter<*>> : WolmoFragment<T>(), IWhatToWhatchView {

    @Inject internal lateinit var sharedPreferencesUtils: SharedPreferencesUtils
    @Inject internal lateinit var toastFactory: ToastFactory

    override fun init() {
        //requireActivity().vToolbar.visibility = View.GONE
        //requireActivity().vToolbarDrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
    }

    protected fun setToolbarData(navigationIcon: Drawable?, title: String) {
        requireActivity().vToolbar.apply {
            this@apply.navigationIcon = navigationIcon
            this.title = title
            this.visibility = View.VISIBLE
            this.setTitleTextColor(resources.getColor(R.color.whatowatch_even_light_grey))
        }
    }

    fun showSnackBar(text: String) {
        val snackBar = Snackbar.make(requireView(), text,
                Snackbar.LENGTH_LONG)
        val snackBarView = snackBar.view
        //snackBarView.background = AppCompatResources.getDrawable(requireContext(), R.drawable.button_dark_grey)
        val params = snackBarView.layoutParams as FrameLayout.LayoutParams
        params.setMargins(HORIZONTAL_MARGIN, TOP_MARGIN, HORIZONTAL_MARGIN, 15)
        snackBarView.layoutParams = params
        snackBar.show()
        //hideLoading()
    }

    protected fun hideKeyboard(){
        val imm = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(requireView().windowToken, 0)
    }



    override fun showError(message: String) {
        showSnackBar(message)
    }

    override fun hideProgressBar(){
        (requireActivity() as MainActivity).hideProgressBar()
    }

    override fun showProgressBar(){
        (requireActivity() as MainActivity).showProgressBar()
    }

    companion object {
        private const val HORIZONTAL_MARGIN = 5
        private const val TOP_MARGIN = 0
    }
}
