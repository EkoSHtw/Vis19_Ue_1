package com.example.visualization_uebung_1


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.visualization_uebung_1.R

class ResultAdapter : RecyclerView.Adapter<ResultAdapter.ViewHolder>() {

    val data = ArrayList<CumulativeResultUnit>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val view = LayoutInflater.from(parent.context).inflate(R.layout.holder_statistic,parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
      
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}

}