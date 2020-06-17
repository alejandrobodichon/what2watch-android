package com.example.whatowatch.ui.main

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import ar.com.wolox.wolmo.core.activity.WolmoActivity
import com.example.whatowatch.R
import com.example.whatowatch.model.FragmentTransactionModel
import com.example.whatowatch.model.TransactionType
import com.example.whatowatch.ui.main.login.LoginFragment
import com.example.whatowatch.utils.SharedPreferencesUtils
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.progress_bar.*
import javax.inject.Inject


class MainActivity : WolmoActivity() {

    @Inject
    internal lateinit var sharedPreferencesUtils: SharedPreferencesUtils

    override fun init() {
        //window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        // Remember that you should never show the action bar if the
        // status bar is hidden, so hide that too if necessary.
        //actionBar?.hide()

        manageFragmentsSlideAnimationNoBack(LoginFragment(),null)
    }

    override fun layout() = R.layout.activity_main

    fun hideToolbar(){
        vToolbar.visibility = View.GONE
    }

    fun showProgressBar() {
        clProgressBar.visibility = View.VISIBLE
    }

    fun hideProgressBar() {
        clProgressBar.visibility = View.GONE
    }

    fun manageFragmentsSlideAnimation(
        _fragmentInstance: Fragment,
        _args: Bundle?
    ) {
        val ftm = FragmentTransactionModel(_fragmentInstance, _args)
        ftm.lAnimations =TransactionType.getSlideSideAnimation()
        ftm.registerBackStackTransaction=true
        hideProgressBar()
        executeFragment(ftm)
    }

    fun manageFragmentsSlideAnimationNoBack(
        _fragmentInstance: Fragment,
        _args: Bundle?
    ) {
        val ftm = FragmentTransactionModel(_fragmentInstance, _args)
        ftm.lAnimations =TransactionType.getSlideSideAnimation()
        ftm.registerBackStackTransaction=false
        hideProgressBar()
        executeFragment(ftm)
    }

    private fun executeFragment(_ftm: FragmentTransactionModel) {
        closeKeyboard()
        val ft =
            supportFragmentManager.beginTransaction()
        _ftm.fragmentInstance.arguments = _ftm.args
        if (_ftm.lAnimations.size == 4) {
            ft.setCustomAnimations(
                _ftm.lAnimations[0],
                _ftm.lAnimations[1],
                _ftm.lAnimations[2],
                _ftm.lAnimations[3]
            )
        }
        ft.replace(_ftm.iContainer, _ftm.fragmentInstance)
        if (_ftm.registerBackStackTransaction) {
            ft.addToBackStack(_ftm.fragmentInstance.javaClass.name)
        }
        ft.commit()
    }

    fun closeKeyboard() {
        val view = currentFocus
        if (view != null) {
            val imm =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    fun backToFragmentPosition(_iPosition: Int) {
        val entry = supportFragmentManager.getBackStackEntryAt(_iPosition)
        supportFragmentManager.popBackStack(
            entry.id,
            FragmentManager.POP_BACK_STACK_INCLUSIVE
        )
        supportFragmentManager.executePendingTransactions()
    }
}
