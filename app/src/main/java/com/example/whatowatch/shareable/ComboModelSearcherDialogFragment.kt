package com.example.whatowatch.shareable

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import android.widget.SearchView
import android.content.DialogInterface
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.whatowatch.R

/**
 *
 */
class ComboModelSearcherDialogFragment: DialogFragment() {
    private var callback: Callback? = null
    //private var items: List<CityModel>? = null
    private var svFilterComboModels: SearchView? = null
    private var rvComboModels: RecyclerView? = null
    private var comboModelAdapter: ComboModelsAdapter? = null
    private var title: String = ""


//    fun newInstance(_items: List<CityModel>, _title: String,  _callback: Callback): ComboModelSearcherDialogFragment {
//        val adFragment = ComboModelSearcherDialogFragment()
//        adFragment.items = _items
//        adFragment.callback = _callback
//        adFragment.title = _title
//        return adFragment
//    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val vRoot = activity!!.layoutInflater.inflate(R.layout.combo_model_searcher_dialog_fragment, null)
        val builder = AlertDialog.Builder(activity)
        builder.setView(vRoot)

        builder.setTitle(title)
        builder.setCancelable(false)
        builder.setNegativeButton("Cancelar", DialogInterface.OnClickListener { dialog, _ ->
            svFilterComboModels?.clearFocus()
            callback?.onResponseError("No hay elemento")
            dialog.dismiss()
        })

        val alertDialog = builder.create()

        svFilterComboModels = vRoot.findViewById(R.id.svFilterComboModels)

        rvComboModels = vRoot.findViewById(R.id.rvComboModels)
//        comboModelAdapter = ComboModelsAdapter(context!!,items!!,object : OnComboModelClick {
//            override fun onClick(comboModel: CityModel) {
//                svFilterComboModels?.clearFocus()
//                callback?.onResponseOk(comboModel)
//                dialog!!.dismiss()
//            }
//        })
        rvComboModels?.adapter = comboModelAdapter
        rvComboModels?.layoutManager = LinearLayoutManager(requireContext())

        this.svFilterComboModels?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                comboModelAdapter?.filter?.filter(newText)

                return false
            }
        })

        return alertDialog
    }
}