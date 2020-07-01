package com.example.whatowatch.shareable

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.whatowatch.R
import com.example.whatowatch.model.UserModel
import kotlinx.android.synthetic.main.adapter_item_friend.view.*



class FriendsAdapter(private val context: Context) :
    ListAdapter<UserModel, FriendsItemsViewHolder>(FriendsDiffCallBack()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendsItemsViewHolder =
        FriendsItemsViewHolder(parent)

    override fun onBindViewHolder(holder: FriendsItemsViewHolder, position: Int) {
        holder.tvUserName.text = getItem(position).username
        holder.itemView.setOnClickListener {
        }
    }
}

class FriendsItemsViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.adapter_item_friend, parent, false)
) {
    val tvUserName: TextView = itemView.tvUserName

}
