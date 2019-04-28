package com.example.visualization_uebung_1


import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.database.sqlite


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


    // Gets the data repository in write mode
    val db = dbHelper.writableDatabase

    // Create a new map of values, where column names are the keys
    val values = ContentValues().apply {
        put(FeedEntry.COLUMN_NAME_TITLE, title)
        put(FeedEntry.COLUMN_NAME_SUBTITLE, subtitle)
    }

    // Insert the new row, returning the primary key value of the new row
    val newRowId = db?.insert(FeedEntry.TABLE_NAME, null, values)


}