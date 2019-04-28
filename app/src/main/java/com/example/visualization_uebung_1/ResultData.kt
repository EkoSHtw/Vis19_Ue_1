package com.example.visualization_uebung_1

import androidx.room.*

@Entity
data class ResultData(
        @PrimaryKey val id: Int,
        @ColumnInfo(name = "shape") val shape: String?,
        @ColumnInfo(name = "scaling") val scaled: Int?,
        @ColumnInfo(name = "correct") val correctGuess: Int?,
        @ColumnInfo(name = "false") val falseGuess: Int?
)