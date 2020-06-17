package com.example.whatowatch.ui.main.welcome

import androidx.core.content.ContextCompat

import com.example.whatowatch.R
import com.example.whatowatch.shareable.IWhatToWhatchView
import com.example.whatowatch.shareable.WhatToWhatchFragment
import com.example.whatowatch.shareable.WhatToWhatchPresenter
import com.example.whatowatch.ui.main.MainActivity
import com.example.whatowatch.ui.main.contentselection.ContentSelectionFragment

import kotlinx.android.synthetic.main.welcome_fragment.*
import javax.inject.Inject


class WelcomeFragment @Inject constructor() : WhatToWhatchFragment<WhatToWhatchPresenter>(), IWhatToWhatchView {

    private var firstTime =true
    override fun layout(): Int = R.layout.welcome_fragment

    override fun init() {
        setToolbarData(ContextCompat.getDrawable(requireContext(),R.drawable.ic_hamburger),"What2Watch")
        setImageBackground()
        if(firstTime){
            showError("Welcome ${sharedPreferencesUtils.user?.name}")
            firstTime= false
        }

        btTryItNow.setOnClickListener {
            (requireActivity() as MainActivity).manageFragmentsSlideAnimation(
                ContentSelectionFragment(true),null)
        }

    }


}