package com.example.whatowatch.ui.main.guest

import android.view.View
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions.bitmapTransform
import com.example.whatowatch.R
import com.example.whatowatch.shareable.WhatToWhatchFragment
import com.example.whatowatch.shareable.WhatToWhatchPresenter
import com.example.whatowatch.ui.main.MainActivity
import com.example.whatowatch.ui.main.contentselection.ContentSelectionFragment
import jp.wasabeef.glide.transformations.BlurTransformation
import kotlinx.android.synthetic.main.fragment_guest.*
import javax.inject.Inject


class GuestFragment @Inject constructor(): WhatToWhatchFragment<WhatToWhatchPresenter>(), IGuestView {

    override fun layout(): Int = R.layout.fragment_guest

    override fun init() {
        disableToolbar()
        setImageBackground()

        btStart.setOnClickListener {
            if(tietName.text!!.isNotEmpty()){
                sharedPreferencesUtils.user?.name = tietName.text.toString()
                (requireActivity()as MainActivity).manageFragmentsSlideAnimation(ContentSelectionFragment(false),null)
            } else {
                showError("You must complete the name to continue.")
            }
        }
    }
}