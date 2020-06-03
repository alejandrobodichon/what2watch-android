package com.example.whatowatch.ui.main.login

import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions.bitmapTransform
import com.example.whatowatch.R
import com.example.whatowatch.shareable.WhatToWhatchFragment
import com.example.whatowatch.ui.main.MainActivity
import com.example.whatowatch.ui.main.guest.GuestFragment
import com.example.whatowatch.ui.main.register.RegisterFragment
import com.example.whatowatch.ui.main.welcome.WelcomeFragment
import jp.wasabeef.glide.transformations.BlurTransformation
import kotlinx.android.synthetic.main.fragment_login.*
import javax.inject.Inject


class LoginFragment @Inject constructor() : WhatToWhatchFragment<LoginPresenter>(), ILoginView {

    override fun layout(): Int = R.layout.fragment_login

    override fun init() {
        (requireActivity() as MainActivity).hideToolbar()
        //setToolbarData(ContextCompat.getDrawable(requireContext(),R.drawable.ic_hamburger),"")
        Glide.with(requireContext()).load(R.mipmap.astrowalk)
            .apply(bitmapTransform(BlurTransformation(22)))
            .into(requireView().findViewById(R.id.ivBackground))

        vLoginButton.setOnClickListener {
            validateLoginInput()
        }

        vRegisterButton.setOnClickListener {
            (requireActivity() as MainActivity).manageFragmentsSlideAnimation(RegisterFragment(),null)
        }

        vGuestButton.setOnClickListener {
            (requireActivity() as MainActivity).manageFragmentsSlideAnimation(GuestFragment(),null)
        }

    }

    private fun validateLoginInput(){
        when {
            vLoginUserInputEditText.text!!.isEmpty() -> showError("You must complete your username to login.")
            vLoginpassInputEditText.text!!.isEmpty() -> showError("You must complete your password to login.")
            else -> presenter.login(vLoginUserInputEditText.text.toString(),vLoginpassInputEditText.text.toString())
        }
    }

    override fun onResume() {
        super.onResume()
        vLoginUserInputEditText.text?.clear()
        vLoginpassInputEditText.text?.clear()
    }

    override fun showError(message: String) {
        showSnackBar(message)
    }

    override fun loginSuccessful() {
        sharedPreferencesUtils.name = vLoginUserInputEditText.text.toString()
        (requireActivity() as MainActivity).manageFragmentsSlideAnimation(WelcomeFragment(),null)
    }
}