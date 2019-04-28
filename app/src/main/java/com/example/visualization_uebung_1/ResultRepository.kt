package com.example.visualization_uebung_1

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData

class ResultRepository(private val resultDao: ResultDao) {

    val resultList: List<ResultData> = resultDao.getAll()

    @WorkerThread
    suspend fun update(result: ResultData) {
        result.correctGuess?.let { resultDao.setCorrectById(it, result.id) }
        result.falseGuess?.let { resultDao.setFalseById(it, result.id) }
    }
}