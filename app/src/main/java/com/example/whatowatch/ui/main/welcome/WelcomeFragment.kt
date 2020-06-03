package com.example.whatowatch.ui.main.welcome

import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions.bitmapTransform
import com.example.whatowatch.R
import com.example.whatowatch.shareable.IWhatToWhatchView
import com.example.whatowatch.shareable.WhatToWhatchFragment
import com.example.whatowatch.shareable.WhatToWhatchPresenter
import com.example.whatowatch.ui.main.MainActivity
import com.example.whatowatch.ui.main.contentselection.ContentSelectionFragment

import jp.wasabeef.glide.transformations.BlurTransformation
import kotlinx.android.synthetic.main.welcome_fragment.*
import javax.inject.Inject


class WelcomeFragment @Inject constructor() : WhatToWhatchFragment<WhatToWhatchPresenter>(), IWhatToWhatchView {

    override fun layout(): Int = R.layout.welcome_fragment

    override fun init() {
//        (requireActivity() as MainActivity).hideToolbar()
        setToolbarData(ContextCompat.getDrawable(requireContext(),R.drawable.ic_hamburger),"What2Watch")
        Glide.with(requireContext()).load(R.mipmap.astrowalk)
            .apply(bitmapTransform(BlurTransformation(22)))
            .into(requireView().findViewById(R.id.ivBackground))

        showError("Welcome ${sharedPreferencesUtils.name}")

        btTryItNow.setOnClickListener {
            (requireActivity() as MainActivity).manageFragmentsSlideAnimation(
                ContentSelectionFragment(true),null)
        }

    }


}