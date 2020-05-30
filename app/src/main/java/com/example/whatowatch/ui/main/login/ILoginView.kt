package com.example.whatowatch.ui.main.login


interface ILoginView {

    fun showError(message: String)
    fun hideProgressBar()
    fun loginSuccessful()

    fun showProgressBar()
}
