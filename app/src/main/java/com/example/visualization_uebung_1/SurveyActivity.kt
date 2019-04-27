package com.example.visualization_uebung_1


import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import kotlinx.android.synthetic.main.activity_survey.*


class SurveyActivity: AppCompatActivity(){

    var fillFragment: TaskFillFragment? = null
    var sliderFragment: TaskSliderFragment? = null
    val result = ArrayList<ScalePair>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_survey)
        val manager = this.supportFragmentManager
        manager.beginTransaction().add( this.fragmentContainer.id, TaskFillFragment.newInstance(), "FillFragment").commit()
    }

    fun setResult(shape: String, input: Int, calculated: Int){
        val pair = ScalePair(shape, calculated, input)
        result.add(pair)
    }
}