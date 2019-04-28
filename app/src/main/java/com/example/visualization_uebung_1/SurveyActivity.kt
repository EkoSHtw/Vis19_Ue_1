package com.example.visualization_uebung_1


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.visualization_uebung_1.TaskFillFragment.EnterListener


import kotlinx.android.synthetic.main.activity_survey.*


class SurveyActivity : AppCompatActivity(), EnterListener {


    val cumulativeResultList = ArrayList<CumulativeResultUnit>()
    var xList = ArrayList<Double>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_survey)
        val manager = this.supportFragmentManager
        val fragment = TaskFillFragment.newInstance()
        manager.beginTransaction().add(this.fragmentContainer.id, fragment, "FillFragment").commit()
        fragment.listener = this
        initResultList()
    }

    override fun onEnter(form: String, scale: Int, guess: Int) {

        var i = 0
        while (i < cumulativeResultList.size) {
            if (cumulativeResultList[i].shape == form && cumulativeResultList[i].scale == scale) {
                if (scale == guess) {
                    cumulativeResultList[i].correctGuessed++
                } else cumulativeResultList[i].falseGuessed++

                xList.add(Math.log(scale.toDouble()/Math.log(guess.toDouble())))
                break
            }
            i++
        }
    }


    fun initResultList() {
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
        fragment.setResults(cumulativeResultList)
        val db = Room.databaseBuilder(
                applicationContext,
                ResultDatabase::class.java, "Vis_Ue_1"
        ).build()

        fragment.xFactor = xList.average()
    }
}