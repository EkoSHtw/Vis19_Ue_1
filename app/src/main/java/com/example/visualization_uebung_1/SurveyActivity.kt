package com.example.visualization_uebung_1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.visualization_uebung_1.TaskFillFragment.EnterListener


import kotlinx.android.synthetic.main.activity_survey.*
import kotlin.math.ln


class SurveyActivity : AppCompatActivity(), EnterListener {


    val cumulativeResultList = ArrayList<CumulativeResultUnit>()
    var xList = ArrayList<Double>()
    var xRectangleList = ArrayList<Double>()
    var xCircleList = ArrayList<Double>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_survey)
        val manager = this.supportFragmentManager
        val fragment = TaskFillFragment.newInstance()
        manager.beginTransaction().add(this.fragmentContainer.id, fragment, "FillFragment").commit()
        fragment.listener = this
        initResultList()
    }

    override fun onEnter(shape: String, scale: Int, guess: Int) {
        var i = 0
        var calculatedX = 0.0
        while (i < cumulativeResultList.size) {
            if (cumulativeResultList[i].shape == shape && cumulativeResultList[i].scale == scale) {
                if (scale == guess) {
                    cumulativeResultList[i].correctGuessed++
                } else cumulativeResultList[i].falseGuessed++
                calculatedX = ln(scale.toDouble())/ln(guess.toDouble())

                if(shape == "Rectangle"){
                    xRectangleList.add(calculatedX)
                } else {
                    xCircleList.add(calculatedX)
                }
                xList.add(calculatedX)
                break
            }
            i++
        }
    }

    private fun initResultList() {
        var i = 2
        while (i < 11) {
            cumulativeResultList.add(CumulativeResultUnit("Rectangle", i))
            i++
        }
        i = 2
        while (i < 11) {
            cumulativeResultList.add(CumulativeResultUnit("Circle", i))
            i++
        }
    }

    override fun onFinish() {
        val manager = this.supportFragmentManager
        val fragment = StatisticsFragment()
        manager.beginTransaction().replace(this.fragmentContainer.id, fragment, "StatisticFragment").commit()

        fragment.setResults(cumulativeResultList.filter { s -> s.falseGuessed > 0 || s.correctGuessed > 0} as ArrayList<CumulativeResultUnit>)
        fragment.xFactor = xList.average()
        fragment.xFactorRect = xRectangleList.average()
        fragment.xFactorCircle = xCircleList.average()
    }
}