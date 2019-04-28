package com.example.visualization_uebung_1

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ResultViewModel(application: Application) : AndroidViewModel(application) {

    private var parentJob = Job()
    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Main
    private val scope = CoroutineScope(coroutineContext)

    private val repository: ResultRepository
    val allWords: List<ResultData>

    init {
        val resultDao = ResultDatabase.getDatabase(application.baseContext).resultDao()
        repository = ResultRepository(resultDao)
        allWords = repository.resultList
    }

    override fun onCleared() {
        super.onCleared()
        parentJob.cancel()
    }
}