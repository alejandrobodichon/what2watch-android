package com.example.whatowatch.shareable

import androidx.recyclerview.widget.DiffUtil
import com.example.whatowatch.model.EmoticonsModel

class EmoticonsDiffCallBack : DiffUtil.ItemCallback<EmoticonsModel>() {

    override fun areItemsTheSame(oldItem: EmoticonsModel, newItem: EmoticonsModel): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: EmoticonsModel, newItem: EmoticonsModel): Boolean {
        return oldItem == newItem
    }
}
