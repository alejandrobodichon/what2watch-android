package com.example.whatowatch.shareable

import androidx.recyclerview.widget.DiffUtil
import com.example.whatowatch.model.ToolbarItem

class ToolbarItemDiffCallBack : DiffUtil.ItemCallback<ToolbarItem>() {

    override fun areItemsTheSame(oldItem: ToolbarItem, newItem: ToolbarItem): Boolean {
        return oldItem.itemTitle == newItem.itemTitle
    }

    override fun areContentsTheSame(oldItem: ToolbarItem, newItem: ToolbarItem): Boolean {
        return oldItem.itemTitle == newItem.itemTitle
    }
}
