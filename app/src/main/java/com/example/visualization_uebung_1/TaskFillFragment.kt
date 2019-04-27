package com.example.visualization_uebung_1

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import kotlinx.android.synthetic.main.fragment_task_enter.*
import android.graphics.drawable.BitmapDrawable
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color


import kotlin.random.Random
import androidx.fragment.app.Fragment
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.OvalShape
import android.graphics.drawable.shapes.RectShape
import android.widget.Toast


class TaskFillFragment : Fragment() {

    var activity: SurveyActivity? = null
    private val randomScale: Int = Random.nextInt(1, 10)
    private var mContext: Context? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_task_enter, null)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setUp()
    }


    companion object {
        fun newInstance(): TaskFillFragment {
            return TaskFillFragment()
        }
    }

    fun setUp() {
        var randomScale: Int = Random.nextInt(1, 8)
        val bitmap: Bitmap = Bitmap.createBitmap(800, 800, Bitmap.Config.ARGB_8888)
        val canvas= Canvas(bitmap)

        val shapeRectangle1: ShapeDrawable = ShapeDrawable(RectShape())

        // rectangle positions
        // for scaling mulitply right and bottom and reduce left and top
        var left = 420
        var top = 420
        var right = 480
        var bottom = 480

        // draw rectangle shape to canvas
        shapeRectangle1.setBounds( left, top, right, bottom)
        shapeRectangle1.paint.color = Color.parseColor("#009944")
        shapeRectangle1.draw(canvas)
        image1.background = BitmapDrawable(mContext?.resources, bitmap)

        var sizeScale = (right - left) * (randomScale - 1)
        val shapeRectangle2 = ShapeDrawable(RectShape())
        val bitmap2: Bitmap = Bitmap.createBitmap(800, 800, Bitmap.Config.ARGB_8888)
        val canvas2 = Canvas(bitmap2)
        shapeRectangle2.setBounds( left - sizeScale, top - sizeScale, right + sizeScale, bottom + sizeScale)
        shapeRectangle2.paint.color = Color.parseColor("#009944")
        shapeRectangle2.draw(canvas2)
        image2.background = BitmapDrawable(mContext?.resources, bitmap2)


        val randomScale2 = Random.nextInt(1, 8)
        sizeScale = (right - left) * (randomScale2 - 1)

        // draw oval shape to canvas
        val bitmap3: Bitmap = Bitmap.createBitmap(800, 800, Bitmap.Config.ARGB_8888)
        val shapeOval1 = ShapeDrawable(OvalShape())
        shapeOval1.setBounds( left, top, right, bottom)
        shapeOval1.paint.color = Color.parseColor("#009191")
        val canvas3 = Canvas(bitmap3)
        shapeOval1.draw(canvas3)
        image3.background = BitmapDrawable(mContext?.resources, bitmap3)

        val bitmap4: Bitmap = Bitmap.createBitmap(800, 800, Bitmap.Config.ARGB_8888)
        val shapeOval2 = ShapeDrawable(OvalShape())
        shapeOval2.setBounds( left - sizeScale, top - sizeScale, right + sizeScale, bottom + sizeScale)
        shapeOval2.paint.color = Color.parseColor("#009191")
        val canvas4 = Canvas(bitmap4)
        shapeOval2.draw(canvas4)
        image4.background = BitmapDrawable(mContext?.resources, bitmap4)

        this.buttonEnter.setOnClickListener {
            if(!editTextFilledAnswer.text.toString().isEmpty()&&!editTextFilledAnswer2.text.toString().isEmpty()) {
                activity?.setResult("Rectangle", randomScale, this.editTextFilledAnswer.text.toString().toInt())
                activity?.setResult("Circle", randomScale2, this.editTextFilledAnswer2.text.toString().toInt())
                editTextFilledAnswer.text.clear()
                editTextFilledAnswer.text.clear()
                setUp()
            } else {
                Toast.makeText(context, "Please fill all fields", Toast.LENGTH_LONG).show()
            }

        }
    }

    /*
    interface EnterListener{
        fun onEnter(){

        }
    }
    */
}