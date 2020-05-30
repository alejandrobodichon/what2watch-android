package com.example.whatowatch.shareable

interface IWhatToWhatchView {
    fun showError(message: String)
    fun hideProgressBar()

    fun showProgressBar()
}