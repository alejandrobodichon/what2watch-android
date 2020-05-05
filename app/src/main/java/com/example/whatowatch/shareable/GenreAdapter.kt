package com.example.whatowatch.shareable

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.whatowatch.R
import com.example.whatowatch.model.GenreModel
import kotlinx.android.synthetic.main.adapter_item_radiobutton.view.*


class GenreAdapter(context: Context) :
    ListAdapter<GenreModel, GenreItemsViewHolder>(GenreDiffCallBack()) {

    private val localContext = context
    private var rowIndex = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreItemsViewHolder =
        GenreItemsViewHolder(parent)

    override fun onBindViewHolder(holder: GenreItemsViewHolder, position: Int) {
        holder.option.text = getItem(position).name
        holder.itemView.setOnClickListener {
            rowIndex = position;
            notifyDataSetChanged();
        }
        if (rowIndex == position) {
            holder.rb.isChecked = true
            holder.clItem.background = localContext.getDrawable(R.drawable.gradient_background)
        } else {
            holder.rb.isChecked = false
            holder.clItem.setBackgroundColor(localContext.getColor(R.color.whatowatch_very_dark_red))
        }
    }
}

class GenreItemsViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.adapter_item_radiobutton, parent, false)
) {
    val option: TextView = itemView.tvGenreText
    val rb = itemView.rbGenre
    val clItem = itemView.clItem
}
