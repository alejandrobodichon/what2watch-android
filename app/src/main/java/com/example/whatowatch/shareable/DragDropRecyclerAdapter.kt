package com.example.whatowatch.shareable

import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.whatowatch.R
import kotlinx.android.synthetic.main.adapter_item_text.view.*
import java.util.*
class DragDropRecyclerAdapter(private val startDragListener: OnStartDragListener) :
    RecyclerView.Adapter<DragDropRecyclerAdapter.ItemViewHolder>(),
    ItemMoveCallbackListener.Listener {
    private var users = emptyList<String>().toMutableList()
    fun setUsers(newUsers: List<String>) {
        users.addAll(newUsers)
    }
    override fun getItemCount(): Int {
        return users.size
    }
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val user = users[position]
        holder.bind(user)
        holder.itemView.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                this.startDragListener.onStartDrag(holder)
            }
            return@setOnTouchListener true
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.adapter_item_text, parent, false)
        return ItemViewHolder(itemView)
    }
    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(text: String) {
            itemView.tvText.text = text
        }
    }
    override fun onRowMoved(fromPosition: Int, toPosition: Int) {
        if (fromPosition < toPosition) {
            for (i in fromPosition until toPosition) {
                Collections.swap(users, i, i + 1)
            }
        } else {
            for (i in fromPosition downTo toPosition + 1) {
                Collections.swap(users, i, i - 1)
            }
        }
        notifyItemMoved(fromPosition, toPosition)
    }
    override fun onRowSelected(itemViewHolder: ItemViewHolder) {
    }
    override fun onRowClear(itemViewHolder: ItemViewHolder) {
    }
}