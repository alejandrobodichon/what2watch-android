package com.example.whatowatch.ui.main.login

import android.view.View
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import ar.com.wolox.wolmo.core.util.ToastFactory
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions.bitmapTransform
import com.example.whatowatch.R
import com.example.whatowatch.ui.main.MainActivity
import com.example.whatowatch.ui.main.cityselection.LoginPresenter
import com.example.whatowatch.ui.main.genreselection.GenreSelectionFragment
import com.example.whatowatch.utils.SharedPreferencesUtils
import jp.wasabeef.glide.transformations.BlurTransformation
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.progress_bar.*
import javax.inject.Inject


class LoginFragment @Inject constructor(): WolmoFragment<LoginPresenter>(), ILoginView {

    @Inject internal lateinit var sharedPreferencesUtils: SharedPreferencesUtils
    @Inject internal lateinit var toastFactory: ToastFactory

    override fun layout(): Int = R.layout.fragment_login

    override fun init() {
        Glide.with(requireContext()).load(R.mipmap.astrowalk)
            .apply(bitmapTransform(BlurTransformation(22)))
            .into(requireView().findViewById(R.id.ivBackground))

        vLoginButton.setOnClickListener {
            (requireActivity()as MainActivity).replaceFragment( GenreSelectionFragment(),R.id.vBaseContent,true,
                "Género",false)
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



}