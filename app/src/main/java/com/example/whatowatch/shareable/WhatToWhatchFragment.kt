package com.example.whatowatch.shareable

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.FrameLayout
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.content.ContextCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager

import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import ar.com.wolox.wolmo.core.presenter.BasePresenter
import ar.com.wolox.wolmo.core.util.ToastFactory
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.whatowatch.R
import com.example.whatowatch.model.ToolbarItem
import com.example.whatowatch.ui.main.MainActivity
import com.example.whatowatch.utils.SharedPreferencesUtils
import com.facebook.drawee.view.SimpleDraweeView
import com.google.android.material.snackbar.Snackbar
import jp.wasabeef.glide.transformations.BlurTransformation
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

abstract class WhatToWhatchFragment<T : BasePresenter<*>> : WolmoFragment<T>(), IWhatToWhatchView {

    @Inject internal lateinit var sharedPreferencesUtils: SharedPreferencesUtils
    @Inject internal lateinit var toastFactory: ToastFactory

    private lateinit var actionBarDrawerToggle: ActionBarDrawerToggle

    private lateinit var drawerHeader: View
    private lateinit var avatarPicture: SimpleDraweeView
    private lateinit var userName: TextView
    protected lateinit var roleArrayString: ArrayList<String>

    override fun init() {
        requireActivity().vToolbarDrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
    }

    protected fun setToolbarData(navigationIcon: Drawable?, title: String) {
        requireActivity().vToolbar.apply {
            this@apply.navigationIcon = navigationIcon
            this.title = title
            this.visibility = View.VISIBLE
            this.setTitleTextColor(resources.getColor(R.color.whatowatch_even_light_grey))
            setDrawer()
        }
    }

    protected fun disableToolbar(){
        requireActivity().vToolbar.visibility = View.GONE
        requireActivity().vToolbarDrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
    }

    fun showSnackBar(text: String) {
        val snackBar = Snackbar.make(requireView(), text,
                Snackbar.LENGTH_LONG)
        val snackBarView = snackBar.view
        val params = snackBarView.layoutParams as FrameLayout.LayoutParams
        params.setMargins(HORIZONTAL_MARGIN, TOP_MARGIN, HORIZONTAL_MARGIN, 15)
        snackBarView.layoutParams = params
        snackBar.show()
    }

    private fun setDrawer(){
        drawerHeader = requireActivity().vToolbarBaseNavView.getHeaderView(0)
        avatarPicture = drawerHeader.findViewById(R.id.vNavHeaderPicture)
        userName = drawerHeader.findViewById(R.id.vNavHeaderName)

        userName.text = sharedPreferencesUtils.user?.name
        requireActivity().vToolbarDrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)

        val actionBarDrawerToggle = ActionBarDrawerToggle(requireActivity(), requireActivity().vToolbarDrawer,
            requireActivity().vToolbar, R.string.open, R.string.close)
        requireActivity().vToolbarDrawer.addDrawerListener(actionBarDrawerToggle)

        actionBarDrawerToggle.drawerArrowDrawable.color =
            ContextCompat.getColor(requireContext(), R.color.whatowatch_blue_button)
        setToolbarItems()
    }

    private fun setToolbarItems(){
        val toolbarItems = ArrayList<ToolbarItem>()
//        toolbarItems.apply {
//            add(ToolbarItem("Watch list",R.drawable.ic_list_24px))
//        }
        requireActivity().vToobarBaseItemRecycler.layoutManager = LinearLayoutManager(requireContext())
        val toolbarItemsAdapter = ToolbarItemsAdapter(requireContext())
        requireActivity().vToobarBaseItemRecycler.adapter = toolbarItemsAdapter
        toolbarItemsAdapter.submitList(toolbarItems)

        toolbarItemsAdapter.setItemClickListener(object : ToolbarItemsAdapter.OnItemClickListener {
            override fun onItemClick(destination: Int) {
                    requireActivity().vToolbarDrawer.closeDrawers()
            }
        })
    }

    protected fun hideKeyboard(){
        val imm = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(requireView().windowToken, 0)
    }

    override fun hideProgressBar() {
        (requireActivity() as MainActivity).hideProgressBar()
    }

    override fun showProgressBar() {
        (requireActivity() as MainActivity).showProgressBar()
    }

    override fun showError(message: String) {
        showSnackBar(message)
    }

    protected fun setImageBackground(){
        Glide.with(requireContext()).load(R.mipmap.astrowalk)
            .apply(RequestOptions.bitmapTransform(BlurTransformation(22)))
            .into(requireView().findViewById(R.id.ivBackground))
    }

    companion object {
        private const val HORIZONTAL_MARGIN = 5
        private const val TOP_MARGIN = 0
    }
}
