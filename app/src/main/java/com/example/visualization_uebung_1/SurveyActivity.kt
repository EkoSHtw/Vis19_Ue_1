package com.example.visualization_uebung_1


import android.content.ContentValues
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.visualization_uebung_1.TaskFillFragment.EnterListener


import kotlinx.android.synthetic.main.activity_survey.*


class SurveyActivity: AppCompatActivity(), EnterListener{



    var fillFragment: TaskFillFragment? = null
    var sliderFragment: TaskSliderFragment? = null
    val dbHelper = FeedReaderDbHelper(this)
    val result = ArrayList<ResultUnit>()
    val cumulativeResultList = ArrayList<CumulativeResultUnit>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_survey)
        val manager = this.supportFragmentManager
        val fragment = TaskFillFragment.newInstance()
        manager.beginTransaction().add( this.fragmentContainer.id, fragment, "FillFragment").commit()
        fragment.listener = this
        initResultList()
    }

    override fun onEnter(form: String, scale: Int, guess: Int) {

        var i = 0
        while(i < cumulativeResultList.size){
            if (cumulativeResultList[i].shape == form && cumulativeResultList[i].scale == scale){
                if(scale == guess){
                    cumulativeResultList[i].correctGuessed++
                }
                else cumulativeResultList[i].falseGuessed++
                break
            }
            i++
        }

        /*
        val pair = ResultUnit(form, scale, guess)
        result.add(pair)
        val db = dbHelper.writableDatabase

        // Create a new map of values, where column names are the keys

        val values = ContentValues().apply {
            put(FeedReaderDbHelper.FeedReaderContract.FeedEntry.COLUMN_FORM, pair.getShape())
            put(FeedReaderDbHelper.FeedReaderContract.FeedEntry.COLUMN_GUESS_VALUE, pair.getUserScale())
            put(FeedReaderDbHelper.FeedReaderContract.FeedEntry.COLUMN_ACTUAL_VALUE,pair.getTrueScale())
        }
        // Insert the new row, returning the primary key value of the new row
        val newRowId = db?.insert(FeedReaderDbHelper.FeedReaderContract.FeedEntry.TABLE_NAME, null, values)
        */
    }


    fun initResultList(){

        var i = 1
        while (i < 11 ){
            cumulativeResultList.add(CumulativeResultUnit("Rectangle", i))
            i++
        }
        i = 1
        while (i < 11 ){
            cumulativeResultList.add(CumulativeResultUnit("Circle", i))
            i++
        }
    }

    override fun onFinish() {
        val manager = this.supportFragmentManager
        val fragment = StatisticsFragment()
        manager.beginTransaction().replace( this.fragmentContainer.id, fragment, "StatisticFragment").commit()
        fragment.setResults(cumulativeResultList)
    }
}