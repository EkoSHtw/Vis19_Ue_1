package com.example.visualization_uebung_1


import android.content.ContentValues
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.database.sqlite.*


import kotlinx.android.synthetic.main.activity_survey.*




class SurveyActivity: AppCompatActivity(){

    var fillFragment: TaskFillFragment? = null
    var sliderFragment: TaskSliderFragment? = null
    val dbHelper = FeedReaderDbHelper(this)
    val result = ArrayList<ResultUnit>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_survey)
        val manager = this.supportFragmentManager
        manager.beginTransaction().add( this.fragmentContainer.id, TaskFillFragment.newInstance(), "FillFragment").commit()
    }

    fun setResult(shape: String, input: Int, calculated: Int){
        val pair = ResultUnit(shape, calculated, input)
        result.add(pair)
        val db = dbHelper.writableDatabase

        // Create a new map of values, where column names are the keys
        val values = ContentValues().apply {
            //put(MainActivity.FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE, title)
            put(MainActivity.FeedReaderContract.FeedEntry.COLUMN_NAME_SUBTITLE, "")
        }

        // Insert the new row, returning the primary key value of the new row
        val newRowId = db?.insert(MainActivity.FeedReaderContract.FeedEntry.TABLE_NAME, null, values)
    }


    // Gets the data repository in write mode



}