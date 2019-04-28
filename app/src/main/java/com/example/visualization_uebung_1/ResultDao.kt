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

    @Query("UPDATE ResultData SET correct= :correct WHERE id = :id")
    fun setCorrectById(correct: Int, id: Int): Int

    @Query("UPDATE ResultData SET false= :falseValue WHERE id = :id")
    fun setFalseById(falseValue: Int, id: Int): Int

    @Insert
    fun insertAll(vararg results: ResultData)

    @Delete
    fun delete(result: ResultData)
}
