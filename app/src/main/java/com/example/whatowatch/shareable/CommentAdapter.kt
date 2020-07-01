package com.example.whatowatch.shareable

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat.getColor
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.whatowatch.R
import com.example.whatowatch.model.CommentModel
import com.example.whatowatch.utils.SharedPreferencesUtils
import kotlinx.android.synthetic.main.adapter_item_comment.view.*
import javax.inject.Inject


class CommentAdapter(private val context: Context, private val sharedPreferencesUtils: SharedPreferencesUtils) :
    ListAdapter<CommentModel, CommentsItemsViewHolder>(CommentsDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentsItemsViewHolder =
        CommentsItemsViewHolder(parent)

    override fun onBindViewHolder(holder: CommentsItemsViewHolder, position: Int) {
        holder.tvUser.text = getItem(position).userName
        holder.tvDetail.text = getItem(position).detail
        holder.itemView.setOnClickListener {
        }
        if (isFriend(getItem(position).id))
            holder.itemView.setBackgroundColor(getColor(context, R.color.whatowatch_dark_green))
        else
            holder.itemView.setBackgroundColor(getColor(context, R.color.black_background))
    }

    private fun isFriend(id: Int?): Boolean {
        var isFriend = false
        if (!sharedPreferencesUtils?.friends?.isNullOrEmpty()!!)
            sharedPreferencesUtils.friends?.forEach {
                if (it.id == id)
                    isFriend = true
            }
        return isFriend
    }
}

class CommentsItemsViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.adapter_item_comment, parent, false)
) {
    val tvUser: TextView = itemView.tvUser
    val tvDetail: TextView = itemView.tvComment

}
