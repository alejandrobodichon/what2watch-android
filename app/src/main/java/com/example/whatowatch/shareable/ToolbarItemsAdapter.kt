package com.example.whatowatch.shareable

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.whatowatch.R
import com.example.whatowatch.model.ToolbarItem
import kotlinx.android.synthetic.main.adapter_toolbar_items.view.*


class ToolbarItemsAdapter(val context: Context) : ListAdapter<ToolbarItem, ToolbarItemsViewHolder>(ToolbarItemDiffCallBack()) {

    private lateinit var itemClickListener: OnItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToolbarItemsViewHolder =
            ToolbarItemsViewHolder(parent)

    override fun onBindViewHolder(holder: ToolbarItemsViewHolder, position: Int) {
        holder.option.text = getItem(position).itemTitle
        holder.icon.setImageDrawable(context.getDrawable(getItem(position).icon))
        holder.itemView.setOnClickListener {
            itemClickListener.onItemClick(getItem(position).destination)
        }
    }

    fun setItemClickListener(listener: OnItemClickListener) {
        itemClickListener = listener
    }

    interface OnItemClickListener {

        fun onItemClick(destination: Int)
    }
}

class ToolbarItemsViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.adapter_toolbar_items, parent, false)
) {
    val option = itemView.findViewById<TextView>(R.id.vToolbarItemText)
    val icon = itemView.ivIcon
}
