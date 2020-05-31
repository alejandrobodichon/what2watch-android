package com.example.whatowatch.ui.main.guest

import android.view.View
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions.bitmapTransform
import com.example.whatowatch.R
import com.example.whatowatch.shareable.WhatToWhatchFragment
import com.example.whatowatch.ui.main.MainActivity
import com.example.whatowatch.ui.main.cityselection.LoginPresenter
import com.example.whatowatch.ui.main.contentselection.ContentSelectionFragment
import jp.wasabeef.glide.transformations.BlurTransformation
import kotlinx.android.synthetic.main.fragment_guest.*
import kotlinx.android.synthetic.main.progress_bar.*
import javax.inject.Inject


class GuestFragment @Inject constructor(): WhatToWhatchFragment<LoginPresenter>(), IGuestView {

    override fun layout(): Int = R.layout.fragment_guest

    override fun init() {
        setToolbarData(ContextCompat.getDrawable(requireContext(),R.drawable.ic_hamburger),"")
        Glide.with(requireContext()).load(R.mipmap.astrowalk)
            .apply(bitmapTransform(BlurTransformation(22)))
            .into(requireView().findViewById(R.id.ivBackground))

        btStart.setOnClickListener {
            if(tietName.text!!.isNotEmpty()){
                sharedPreferencesUtils.name = tietName.text.toString()
                (requireActivity()as MainActivity).replaceFragment( ContentSelectionFragment(false),R.id.vBaseContent,true,
                    "Content",false)
                hideKeyboard()
            } else {
                showError("You must complete the name to continue.")
            }
        }
    }

    override fun onResume() {
        super.onResume()
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

}