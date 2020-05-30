package com.example.whatowatch.ui.main.register

import android.view.View
import android.widget.SeekBar
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions.bitmapTransform
import com.example.whatowatch.R
import com.example.whatowatch.shareable.WhatToWhatchFragment
import com.example.whatowatch.ui.main.MainActivity
import com.example.whatowatch.ui.main.cityselection.LoginPresenter
import jp.wasabeef.glide.transformations.BlurTransformation
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_register.*
import kotlinx.android.synthetic.main.progress_bar.*
import javax.inject.Inject


class RegisterFragment @Inject constructor(): WhatToWhatchFragment<LoginPresenter>(), IRegisterView {

    override fun layout(): Int = R.layout.fragment_register

    override fun init() {
        (requireActivity() as MainActivity).hideToolbar()
        //setToolbarData(ContextCompat.getDrawable(requireContext(),R.drawable.ic_hamburger),"")
        Glide.with(requireContext()).load(R.mipmap.astrowalk)
            .apply(bitmapTransform(BlurTransformation(22)))
            .into(requireView().findViewById(R.id.ivBackground))
        seekBar1.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                tvNumber.text = progress.toString()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
               // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        } )


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