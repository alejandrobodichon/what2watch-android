package com.example.whatowatch.shareable

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.LinearLayout
import android.widget.TextView
import com.example.whatowatch.R
import com.example.whatowatch.model.GenreModel
import java.util.ArrayList

/**
 */
class ComboModelsAdapter(private val context: Context,
                         private var comboModels: List<GenreModel>,
                         private val onItemClick: OnComboModelClick): RecyclerView.Adapter<ComboModelsAdapter.ViewHolder>(), Filterable {

    private val lComboModels: MutableList<GenreModel> = ArrayList()
    private var lComboModelsFiltered: MutableList<GenreModel> = ArrayList()
    private var valueFilter = ValueFilter()

    init {
        this.lComboModels.clear()
        this.lComboModels.addAll(comboModels)
        this.lComboModelsFiltered.clear()
        this.lComboModelsFiltered.addAll(comboModels)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return lComboModelsFiltered?.count()
    }

    override fun getFilter(): Filter {
        return valueFilter
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val bItem = this.lComboModelsFiltered.get(position)
        holder.llComboModel.setOnClickListener {onItemClick.onClick(bItem) }
        holder.tvDescription.text = bItem.name + " - " + bItem.name
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        return ViewHolder(inflater.inflate(R.layout.combo_model_lists_item, parent, false))
    }

    private inner class ValueFilter : Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val results = FilterResults()
            val sConstraint = constraint!!.toString().toLowerCase()
            if ( constraint.isNotEmpty() ) {
                val filterList = ArrayList<GenreModel>()
                for (model in lComboModels) {
                    if (!model.name.isNullOrEmpty() && model.name?.toLowerCase().contains(sConstraint)) {
                        filterList.add(model)
                    }
                }
                results.count = filterList.size
                results.values = filterList
            } else {
                results.count = lComboModels.size
                results.values = lComboModels
            }
            return results
        }

        override fun publishResults(constraint: CharSequence, results: FilterResults) {
            lComboModelsFiltered.clear()
            lComboModelsFiltered.addAll(results.values as Collection<GenreModel>)

            notifyDataSetChanged()
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvDescription: TextView
        var llComboModel: LinearLayout


        init {
            llComboModel = itemView.findViewById(R.id.llComboModel)
            tvDescription = itemView.findViewById(R.id.tvComboModelDescription)
        }
    }


}