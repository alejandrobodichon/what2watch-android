package com.example.whatowatch.ui.main.profileregister

import android.view.View
import android.widget.ArrayAdapter
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ItemTouchHelper.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions.bitmapTransform
import com.example.whatowatch.R
import com.example.whatowatch.shareable.DragDropRecyclerAdapter
import com.example.whatowatch.shareable.ItemMoveCallbackListener
import com.example.whatowatch.shareable.OnStartDragListener
import com.example.whatowatch.shareable.WhatToWhatchFragment
import com.example.whatowatch.ui.main.MainActivity
import com.example.whatowatch.ui.main.cityselection.LoginPresenter
import jp.wasabeef.glide.transformations.BlurTransformation
import kotlinx.android.synthetic.main.profile_register_fragment.*
import kotlinx.android.synthetic.main.progress_bar.*
import javax.inject.Inject


class ProfileRegisterFragment @Inject constructor(): WhatToWhatchFragment<LoginPresenter>(), IProfileRegisterView,
    OnStartDragListener {
    lateinit var adapter: DragDropRecyclerAdapter
    lateinit var touchHelper: ItemTouchHelper

    override fun layout(): Int = R.layout.profile_register_fragment

    override fun init() {
        //(requireActivity() as MainActivity).hideToolbar()
        setToolbarData(ContextCompat.getDrawable(requireContext(),R.drawable.ic_hamburger),"")
        Glide.with(requireContext()).load(R.mipmap.astrowalk)
            .apply(bitmapTransform(BlurTransformation(22)))
            .into(requireView().findViewById(R.id.ivBackground))

        adapter = DragDropRecyclerAdapter(this)
        populateListItem()
        val callback: ItemTouchHelper.Callback = ItemMoveCallbackListener(adapter)
        touchHelper = ItemTouchHelper(callback)
        touchHelper.attachToRecyclerView(rvGenreSelection)
        rvGenreSelection.layoutManager = LinearLayoutManager(requireContext())
        rvGenreSelection.adapter = adapter
    }
    override fun onStartDrag(viewHolder: RecyclerView.ViewHolder) {
        touchHelper.startDrag(viewHolder)
    }
    private fun populateListItem() {
        val users = listOf(
            "Comedy",
            "Horror",
            "Romance",
            "Action",
            "Thriller",
            "Drama",
            "Animation",
            "Fantasy"

        )
        adapter.setUsers(users)
    }


    override fun onResume() {
        super.onResume()
    }


}