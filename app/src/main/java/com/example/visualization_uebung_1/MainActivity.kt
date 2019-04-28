package com.example.visualization_uebung_1

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.database.sqlite.*
import android.provider.BaseColumns

import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*





class MainActivity : AppCompatActivity() {

    object FeedReaderContract {
        // Table contents are grouped together in an anonymous object.
        object FeedEntry : BaseColumns {
            const val TABLE_NAME = "vis"
            const val COLUMN_NAME_TITLE = "participant_1"
            const val COLUMN_NAME_SUBTITLE = "participant for circle"
        }
    }


    private var mContext: Context? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mContext = applicationContext
        this.buttonStart.setOnClickListener {
            val intent = Intent(this, SurveyActivity::class.java)
            startActivity(intent)
        }
    }

    fun getContext(): Context? {
        //  return instance.getApplicationContext();
        return mContext
    }

    companion object {
        private const val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS ${FeedReaderContract.FeedEntry.TABLE_NAME}"
        private const val SQL_CREATE_ENTRIES =
                "CREATE TABLE ${FeedReaderContract.FeedEntry.TABLE_NAME} (" +
                        "${BaseColumns._ID} INTEGER PRIMARY KEY," +
                        "${FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE} TEXT," +
                        "${FeedReaderContract.FeedEntry.COLUMN_NAME_SUBTITLE} TEXT)"
    }

}
