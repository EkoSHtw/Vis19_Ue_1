package com.example.visualization_uebung_1

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import kotlinx.android.synthetic.main.fragment_task_enter.*
import android.graphics.drawable.BitmapDrawable
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color


import kotlin.random.Random
import androidx.fragment.app.Fragment
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.OvalShape
import android.graphics.drawable.shapes.RectShape
import android.widget.Toast
import android.view.ViewTreeObserver




class TaskFillFragment : Fragment() {

    var activity: SurveyActivity? = null
    private var mContext: Context? = null
    var listener: EnterListener? =  null

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_task_enter, null)
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

    private fun setUp() {
        val randomScale: Int = Random.nextInt(2, 10)
        val randomScale2 = Random.nextInt(2, 10)

        val vto = image2.viewTreeObserver
        vto.addOnPreDrawListener(object : ViewTreeObserver.OnPreDrawListener {
            override fun onPreDraw(): Boolean {
                // Remove after the first run so it doesn't fire forever
                image2.viewTreeObserver.removeOnPreDrawListener(this)
                val scaleValue =  Math.sqrt((image2.maxHeight * image2.maxWidth * randomScale).toDouble())
                image2.scaleX = scaleValue.toFloat()
                image2.scaleY = scaleValue.toFloat()
                return true
            }
        })

        val vt = image4.viewTreeObserver
        vt.addOnPreDrawListener(object : ViewTreeObserver.OnPreDrawListener {
            override fun onPreDraw(): Boolean {
                // Remove after the first run so it doesn't fire forever
                image4.viewTreeObserver.removeOnPreDrawListener(this)
                val scaleValue = Math.sqrt((image4.maxHeight * image4.maxWidth * randomScale2).toDouble())
                image4.scaleX = scaleValue.toFloat()
                image4.scaleY = scaleValue.toFloat()
                return true
            }
        })

        this.buttonEnter.setOnClickListener {
            if(!editTextFilledAnswer.text.toString().isEmpty() && !editTextFilledAnswer2.text.toString().isEmpty()) {
                listener?.onEnter("Rectangle", randomScale, this.editTextFilledAnswer.text.toString().toInt())
                listener?.onEnter("Circle", randomScale2, this.editTextFilledAnswer2.text.toString().toInt())
                editTextFilledAnswer.text.clear()
                editTextFilledAnswer2.text.clear()
                setUp()
            } else {
                Toast.makeText(context, "Please fill out all fields", Toast.LENGTH_LONG).show()
            }
        }

        this.buttonFinish.setOnClickListener{
            if(!editTextFilledAnswer.text.toString().isEmpty() && !editTextFilledAnswer2.text.toString().isEmpty()) {
                listener?.onEnter("Rectangle", randomScale, this.editTextFilledAnswer.text.toString().toInt())
                listener?.onEnter("Circle", randomScale2, this.editTextFilledAnswer2.text.toString().toInt())
                editTextFilledAnswer.text.clear()
                editTextFilledAnswer2.text.clear()
            }
            listener?.onFinish()
        }
    }

    interface EnterListener{
        fun onEnter(form: String, scale: Int, guess: Int)
        fun onFinish()
    }
}