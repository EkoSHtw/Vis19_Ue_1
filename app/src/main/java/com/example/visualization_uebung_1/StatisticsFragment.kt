package com.example.visualization_uebung_1

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_statistics.*
import kotlinx.android.synthetic.main.fragment_statistics.view.*

class StatisticsFragment :Fragment(){

    var result = ArrayList<CumulativeResultUnit>()
    var adapter: ResultAdapter? = null


    override fun onAttach(context: Context?) {
        super.onAttach(context)
    }

    fun setResults(result: ArrayList<CumulativeResultUnit>){
        if(adapter != null){
            adapter?.setData(result)
        }
        this.result = result
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
       val view =  inflater.inflate(R.layout.fragment_statistics, null)
        adapter = context?.let { ResultAdapter(it) }
        adapter?.setData(result)

        view.recyclerView.layoutManager = LinearLayoutManager(activity);
        view.recyclerView.adapter = adapter
        return view
    }
}