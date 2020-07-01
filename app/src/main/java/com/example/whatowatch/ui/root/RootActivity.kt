package com.example.whatowatch.ui.root

import android.content.Intent
import android.os.CountDownTimer
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import ar.com.wolox.wolmo.core.activity.WolmoActivity
import com.example.whatowatch.R
import com.example.whatowatch.model.UserModel
import com.example.whatowatch.ui.main.MainActivity
import com.example.whatowatch.ui.main.login.LoginFragment
import com.example.whatowatch.utils.SharedPreferencesUtils
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import javax.inject.Inject


class RootActivity : WolmoActivity() {

    @Inject
    internal lateinit var sharedPreferencesUtils: SharedPreferencesUtils

    override fun init() {
        //window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        // Remember that you should never show the action bar if the
        // status bar is hidden, so hide that too if nece
        //ssary.
        actionBar?.hide()
        sharedPreferencesUtils.isRegistered = false
//        sharedPreferencesUtils.users = listOf(
//            UserModel("Juan","Juan","Juan","Juan","Juan","Juan"),
//            UserModel("Juan","Juan","Fernando","Juan","Juan","Juan"),
//            UserModel("Juan","Juan","Alejandro","Juan","Juan","Juan"),
//            UserModel("Juan","Juan","Esteban","Juan","Juan","Juan"),
//            UserModel("Juan","Juan","Pablo","Juan","Juan","Juan"),
//            UserModel("Juan","Juan","Maximiliano","Juan","Juan","Juan"),
//            UserModel("Juan","Juan","Gonzalo","Juan","Juan","Juan")
//        )

        object : CountDownTimer(3000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
            }

            override fun onFinish() {
                goToAmountFragment()
            }
        }.start()
    }

    private fun goToAmountFragment(){
        startActivity( Intent(this, MainActivity::class.java).apply { addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK) })
    }

    override fun layout() = R.layout.root_activity


}
