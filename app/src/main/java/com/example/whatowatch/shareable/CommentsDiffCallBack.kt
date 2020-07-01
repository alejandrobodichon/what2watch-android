package com.example.whatowatch.shareable

import androidx.recyclerview.widget.DiffUtil
import com.example.whatowatch.model.CommentModel
import com.example.whatowatch.model.EmoticonsModel
import com.example.whatowatch.model.UserModel

class CommentsDiffCallBack : DiffUtil.ItemCallback<CommentModel>() {

    override fun areItemsTheSame(oldItem: CommentModel, newItem: CommentModel): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: CommentModel, newItem: CommentModel): Boolean {
        return oldItem == newItem
    }
}
