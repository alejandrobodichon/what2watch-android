package com.example.whatowatch.shareable

import com.example.whatowatch.model.GenreModel
import com.example.whatowatch.model.UserModel

interface OnComboModelClick {
    fun onClick(comboModel: UserModel)
}