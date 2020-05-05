package com.example.whatowatch.shareable

import androidx.recyclerview.widget.DiffUtil
import com.example.whatowatch.model.GenreModel

class GenreDiffCallBack : DiffUtil.ItemCallback<GenreModel>() {

    override fun areItemsTheSame(oldItem: GenreModel, newItem: GenreModel): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: GenreModel, newItem: GenreModel): Boolean {
        return oldItem == newItem
    }
}
