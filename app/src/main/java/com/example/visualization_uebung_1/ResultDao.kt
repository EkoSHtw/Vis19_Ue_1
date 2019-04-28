package com.example.visualization_uebung_1

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ResultDao {
    @Query("SELECT * FROM ResultData")
    fun getAll(): List<ResultData>

    @Query("SELECT * FROM ResultData WHERE id IN (:ids)")
    fun loadAllByIds(ids: IntArray): List<ResultData>

    @Query("SELECT * FROM result WHERE shape LIKE :shape AND " + "")
    fun findByShape(shape: String): ResultData

    @Query("Select * FROM ResultData")
    fun loadAll():List<ResultData>

    @Insert
    fun insertAll(vararg results: ResultData)

    @Delete
    fun delete(result: ResultData)
}