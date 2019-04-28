package com.example.visualization_uebung_1

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns

class FeedReaderDbHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(Companion.SQL_CREATE_ENTRIES)
    }
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(Companion.SQL_DELETE_ENTRIES)
        onCreate(db)
    }
    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }
    companion object {
        // If you change the database schema, you must increment the database version.
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "FeedReader.db"

        private const val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS ${FeedReaderContract.FeedEntry.TABLE_NAME}"
        private const val SQL_CREATE_ENTRIES =
                "CREATE TABLE ${FeedReaderContract.FeedEntry.TABLE_NAME} (" +
                        "${BaseColumns._ID} INTEGER PRIMARY KEY," +
                        "${FeedReaderContract.FeedEntry.COLUMN_FORM} TEXT)" +
                        "${FeedReaderContract.FeedEntry.COLUMN_GUESS_VALUE} INTEGER)"+
                        "${FeedReaderContract.FeedEntry.COLUMN_ACTUAL_VALUE} INTEGER)"
    }

    object FeedReaderContract {
        // Table contents are grouped together in an anonymous object.
        object FeedEntry : BaseColumns {
            const val TABLE_NAME = "vis"
            const val COLUMN_NAME_TITLE = "participant_1"
            const val COLUMN_FORM = "participation_form"
            const val COLUMN_GUESS_VALUE = "guessed_value"
            const val COLUMN_ACTUAL_VALUE = "actual_value"
        }
    }
}