package com.example.whatowatch.ui.main.register

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.widget.SeekBar
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions.bitmapTransform
import com.example.whatowatch.R
import com.example.whatowatch.model.UserModel
import com.example.whatowatch.shareable.WhatToWhatchFragment
import com.example.whatowatch.ui.main.MainActivity
import jp.wasabeef.glide.transformations.BlurTransformation
import kotlinx.android.synthetic.main.fragment_register.*
import kotlinx.android.synthetic.main.progress_bar.*
import java.util.*
import javax.inject.Inject


class RegisterFragment @Inject constructor() : WhatToWhatchFragment<RegisterPresenter>(),
    IRegisterView {

    private var mDateSetListener: OnDateSetListener? = null

    override fun layout(): Int = R.layout.fragment_register

    override fun init() {
        (requireActivity() as MainActivity).hideToolbar()
        //setToolbarData(ContextCompat.getDrawable(requireContext(),R.drawable.ic_hamburger),"")
        Glide.with(requireContext()).load(R.mipmap.astrowalk)
            .apply(bitmapTransform(BlurTransformation(22)))
            .into(requireView().findViewById(R.id.ivBackground))


        tietBirthday.setOnFocusChangeListener { v, hasFocus -> if (hasFocus) showDatePickerDialog() }

        mDateSetListener =
            OnDateSetListener { datePicker, year, month, day ->
                var month = month
                month += 1
                val parsedMonth: String
                parsedMonth = if(month>9) {
                    month.toString()
                } else "0$month"

                val date = "$year-$parsedMonth-$day"
                tietBirthday.setText(date)
            }

        btRegister.setOnClickListener { validateFields() }

    }

    private fun validateFields() {
        when {
            tietName.text!!.isEmpty() -> showError("You need to complete the Name to register.")
            tietLastName.text!!.isEmpty() -> showError("You need to complete the last name to register.")
            tietBirthday.text!!.isEmpty() -> showError("You need to complete the Birthday to register.")
            tietEmail.text!!.isEmpty() -> showError("You need to complete the Email to register.")
            tietUserName.text!!.isEmpty() -> showError("You need to complete the Username to register.")
            tietPassword.text!!.isEmpty() -> showError("You need to complete the Password to register.")
            tietRepeatPassword.text!!.isEmpty() -> showError("You need to complete the Repeat password to register.")
            tietRepeatPassword.text.toString() != tietPassword.text.toString() -> showError("You need to put the same password.")
            !cbTerms.isChecked -> showError("You need to accept the terms and conditions.")
            else -> presenter.register(
                UserModel(
                    tietName.text.toString(),
                    tietLastName.text.toString(),
                    tietUserName.text.toString(),
                    tietPassword.text.toString(),
                    tietEmail.text.toString(),
                    tietBirthday.text.toString()
                )
            )
        }
    }

    private fun showDatePickerDialog() {
        val cal: Calendar = Calendar.getInstance()
        val year: Int = cal.get(Calendar.YEAR)
        val month: Int = cal.get(Calendar.MONTH)
        val day: Int = cal.get(Calendar.DAY_OF_MONTH)

        val dialog = DatePickerDialog(
            requireContext(),
            R.style.DatePickerTheme,
            mDateSetListener,
            year, month, day
        )
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()
    }

    override fun goBack() {
        (requireActivity() as MainActivity).backToFragmentPosition(0)
    }

}