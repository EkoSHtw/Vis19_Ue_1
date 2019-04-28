package com.example.visualization_uebung_1

import androidx.room.*

@Dao
interface ResultDao {
    @Query("SELECT * FROM ResultData")
    fun getAll(): List<ResultData>

    @Query("SELECT * FROM ResultData WHERE id IN (:ids)")
    fun loadAllByIds(ids: IntArray): List<ResultData>

    @Query("SELECT * FROM ResultData WHERE shape LIKE :shape")
    fun findByShape(shape: String): ResultData

    @Insert
    fun insertAll(vararg results: ResultData)

    @Delete
    fun delete(result: ResultData)
}