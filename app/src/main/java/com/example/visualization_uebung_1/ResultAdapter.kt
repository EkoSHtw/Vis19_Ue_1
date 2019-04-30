package com.example.visualization_uebung_1


import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RectShape
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.visualization_uebung_1.R
import kotlinx.android.synthetic.main.holder_statistic.*
import kotlinx.android.synthetic.main.holder_statistic.view.*

class ResultAdapter(val context: Context) : RecyclerView.Adapter<ResultAdapter.ViewHolder>() {

    private var data = ArrayList<CumulativeResultUnit>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.holder_statistic, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun setData(data: ArrayList<CumulativeResultUnit>){
        this.data = data;
        notifyDataSetChanged()
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val result = data[position]
        holder.itemView.scaledValue.text = "Scaled Value: ${result.scale}"
        holder.itemView.textShape.text = result.shape
        val correctAmount = result.correctGuessed
        val falseAmount = result.falseGuessed

        holder.itemView.textCorrectGuess.text = "Correct guesses ${result.correctGuessed}"
        holder.itemView.textFalseGuess.text = "False guesses ${result.falseGuessed}"
        if (falseAmount == 0 && correctAmount > 0) {
            setBar(holder.itemView.imageCorrectBar, 1)
            setBar(holder.itemView.imageFalseBar, 0)
        }
        else if (correctAmount == 0 && falseAmount > 0) {
            setBar(holder.itemView.imageCorrectBar, 0)
            setBar(holder.itemView.imageFalseBar, 1)
        }
        else if (correctAmount > 0 && falseAmount > 0) {
            val fullBar = falseAmount+correctAmount
            setBar(holder.itemView.imageCorrectBar, fullBar/correctAmount)
            setBar(holder.itemView.imageFalseBar, fullBar/falseAmount)
        }
        else {
            setBar(holder.itemView.imageCorrectBar, 0)
            setBar(holder.itemView.imageFalseBar, 0)
        }
    }

    private fun setBar(view: ImageView, ratio: Int) {

        val bitmap: Bitmap = Bitmap.createBitmap(800, 200, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        val shapeRectangle1: ShapeDrawable = ShapeDrawable(RectShape())

        // rectangle positions
        // for scaling mulitply right and bottom and reduce left and top
        var left = 0
        var top = 0
        var right = 800
        var bottom = 200

        if (ratio != 0){
            right /= ratio
        } else
            right = 0

        // draw rectangle shape to canvas
        shapeRectangle1.setBounds(left, top, right, bottom)
        shapeRectangle1.paint.color = Color.parseColor("#005544")
        shapeRectangle1.draw(canvas)
        view.background = BitmapDrawable(context.resources, bitmap)
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}

}