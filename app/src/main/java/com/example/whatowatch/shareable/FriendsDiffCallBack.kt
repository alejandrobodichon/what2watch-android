package com.example.whatowatch.shareable

import androidx.recyclerview.widget.DiffUtil
import com.example.whatowatch.model.EmoticonsModel
import com.example.whatowatch.model.UserModel

class FriendsDiffCallBack : DiffUtil.ItemCallback<UserModel>() {

    override fun areItemsTheSame(oldItem: UserModel, newItem: UserModel): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: UserModel, newItem: UserModel): Boolean {
        return oldItem == newItem
    }
}
