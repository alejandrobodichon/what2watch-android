package com.example.whatowatch.ui.main.emoticons


import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager

import com.example.whatowatch.R
import com.example.whatowatch.model.EmoticonsModel
import com.example.whatowatch.model.EmotionsModel
import com.example.whatowatch.shareable.EmoticonsAdapter
import com.example.whatowatch.shareable.WhatToWhatchFragment
import com.example.whatowatch.ui.main.MainActivity
import com.example.whatowatch.ui.main.recomendationdetail.RecomendationDetailFragment
import kotlinx.android.synthetic.main.emoticons_fragment.*
import kotlinx.android.synthetic.main.emotions_fragment.btRecommend
import javax.inject.Inject


class EmoticonsFragment @Inject constructor(val content: String): WhatToWhatchFragment<EmoticonsPresenter>(), IEmoticonsView{

    override fun layout(): Int = R.layout.emoticons_fragment

    override fun init() {
        setToolbarData(ContextCompat.getDrawable(requireContext(),R.drawable.ic_hamburger),"")
        setImageBackground()

        val emoticons1 = listOf(EmoticonsModel("1",R.drawable.ic_happy_2), EmoticonsModel("2",R.drawable.ic_happy_4),EmoticonsModel("3",R.drawable.ic_crying_1),
            EmoticonsModel("4",R.drawable.ic_in_love), EmoticonsModel("5",R.drawable.ic_surprised_3),EmoticonsModel("6",R.drawable.ic_surprised),
            EmoticonsModel("7",R.drawable.ic_crying), EmoticonsModel("8",R.drawable.ic_angry),EmoticonsModel("9",R.drawable.ic_shocked),
            EmoticonsModel("10",R.drawable.ic_crying_2), EmoticonsModel("11",R.drawable.ic_sad),EmoticonsModel("12",R.drawable.ic_scare))
        rvEmoticon1.layoutManager = GridLayoutManager(requireContext(),3)
        rvEmoticon1.adapter = EmoticonsAdapter(requireContext()).also {
            it.submitList(emoticons1)
        }

        btRecommend.setOnClickListener {
            (requireActivity() as MainActivity).manageFragmentsSlideAnimation( RecomendationDetailFragment(content,"comedy", null),null)
        }
    }
}