package com.example.whatowatch.shareable

import com.example.whatowatch.model.GenreModel

interface OnComboModelClick {
    fun onClick(comboModel: GenreModel)
}