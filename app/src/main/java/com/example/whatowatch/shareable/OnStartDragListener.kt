package com.example.whatowatch.shareable

import androidx.recyclerview.widget.RecyclerView

interface OnStartDragListener {
    fun onStartDrag(viewHolder: RecyclerView.ViewHolder)
}