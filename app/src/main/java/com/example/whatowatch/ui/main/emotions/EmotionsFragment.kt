package com.example.whatowatch.ui.main.emotions


import android.widget.SeekBar
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions.bitmapTransform
import com.example.whatowatch.R
import com.example.whatowatch.model.EmotionsModel
import com.example.whatowatch.shareable.WhatToWhatchFragment
import com.example.whatowatch.ui.main.MainActivity
import com.example.whatowatch.ui.main.recomendationdetail.RecomendationDetailFragment
import jp.wasabeef.glide.transformations.BlurTransformation
import kotlinx.android.synthetic.main.emotions_fragment.*
import javax.inject.Inject


class EmotionsFragment @Inject constructor(val content: String): WhatToWhatchFragment<EmotionsPresenter>(), IEmotionsView{

    override fun layout(): Int = R.layout.emotions_fragment

    override fun init() {
        //(requireActivity() as MainActivity).hideToolbar()
        setToolbarData(ContextCompat.getDrawable(requireContext(),R.drawable.ic_hamburger),"")
        Glide.with(requireContext()).load(R.mipmap.astrowalk)
            .apply(bitmapTransform(BlurTransformation(22)))
            .into(requireView().findViewById(R.id.ivBackground))

        sbAnger.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                tvNumberAnger.text = progress.toString()
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        sbDisgust.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                tvNumberDisgust.text = progress.toString()
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        sbSad.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                tvNumberSad.text = progress.toString()
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        sbHappy.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                tvNumberHappy.text = progress.toString()
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        sbSurprise.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                tvNumberSurprise.text = progress.toString()
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        sbFear.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                tvNumberFear.text = progress.toString()
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
        btRecommend.setOnClickListener {
            (requireActivity() as MainActivity).manageFragmentsSlideAnimation( RecomendationDetailFragment(content,null, EmotionsModel(tvNumberAnger.text.toString(),
                tvNumberDisgust.text.toString(),
                tvNumberSad.text.toString(),
                tvNumberHappy.text.toString(),
                tvNumberSurprise.text.toString(),
                tvNumberFear.text.toString())
            ),null)
        }

    }


    override fun onResume() {
        super.onResume()
    }


}