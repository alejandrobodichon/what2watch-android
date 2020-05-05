package com.example.whatowatch.ui.main

import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import ar.com.wolox.wolmo.core.activity.WolmoActivity
import com.example.whatowatch.R
import com.example.whatowatch.ui.main.login.LoginFragment
import com.example.whatowatch.utils.SharedPreferencesUtils
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import javax.inject.Inject


class MainActivity : WolmoActivity() {

    @Inject
    internal lateinit var sharedPreferencesUtils: SharedPreferencesUtils

    override fun init() {
        //window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        // Remember that you should never show the action bar if the
        // status bar is hidden, so hide that too if necessary.
        //actionBar?.hide()

        replaceFragment(R.id.vBaseContent, LoginFragment())
    }

    override fun layout() = R.layout.activity_main

    fun replaceFragment(
        fragment: Fragment, resId: Int,
        addToBackStack: Boolean, title: String?, popToBackstack: Boolean
    ): Fragment? {
        supportFragmentManager.beginTransaction().apply {
            if (addToBackStack) {
                addToBackStack(null)
            }
            if (popToBackstack)
                supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
            commitAllowingStateLoss()
            setTitle(title)
            replace(resId, fragment)
            return fragment
        }

    }

    fun showSnackBar(parent: View, message: String) {
        Snackbar.make(parent, message, Snackbar.LENGTH_LONG).show()
    }
}
