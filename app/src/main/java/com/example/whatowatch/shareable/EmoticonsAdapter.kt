package com.example.whatowatch.shareable

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.whatowatch.R
import com.example.whatowatch.model.EmoticonsModel
import kotlinx.android.synthetic.main.adapter_item_imageview.view.*


class EmoticonsAdapter(private val context: Context) :
    ListAdapter<EmoticonsModel, EmoticonsItemsViewHolder>(EmoticonsDiffCallBack()) {

    private var list = mutableListOf<Int>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmoticonsItemsViewHolder =
        EmoticonsItemsViewHolder(parent)

    override fun onBindViewHolder(holder: EmoticonsItemsViewHolder, position: Int) {
        holder.ivEmoticon.setImageDrawable(context.getDrawable(getItem(position).drawable))
        holder.itemView.setOnClickListener {
            if(list.contains(position))
                list.remove(position)
            else
                addToQueue(position)
            notifyDataSetChanged()
        }
        if (list.contains(position)) {
            holder.ivEmoticon.foreground = ColorDrawable(context.getColor(R.color.whatowatch_transparent))
        } else {
            holder.ivEmoticon.foreground = context.getDrawable(R.drawable.rounded_layout)
        }
    }
    fun getChecked(): List<EmoticonsModel>?{
        val selectedEmoticons = mutableListOf<EmoticonsModel>()
        list.forEach { selectedEmoticons.add(getItem(it)) }
        return selectedEmoticons
    }

    private fun addToQueue(position: Int){
        if(list.size>=3){
            list[0]=list[1]
            list[1]=list[2]
            list[2] = position
        } else{
            list.add(position)
        }
    }

}

class EmoticonsItemsViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.adapter_item_imageview, parent, false)
) {
    val ivEmoticon: ImageView = itemView.ivEmoticon

}
