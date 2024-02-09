package com.example.englishlearningapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.englishlearningapp.EngLearningApplication
import com.example.englishlearningapp.data.local.Content
import com.example.englishlearningapp.data.local.Content2
import com.example.englishlearningapp.data.local.ContentsDao
import kotlinx.coroutines.flow.Flow

class EngLearningViewModel(private val contentsDao: ContentsDao): ViewModel() {

    // Get full bus schedule from Room DB
    fun getFull(): Flow<List<Content>> = contentsDao.getAll()

    // Get full bus schedule from Room DB
    fun getFullSecondTable(): Flow<List<Content2>> = contentsDao.getAllSecondTable()

    // Get bus schedule based on the stop name from Room DB
    fun getOnlyPassed(): Flow<List<Content>> =
        contentsDao.getOnlyPassed()

    companion object {
        val factory : ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as EngLearningApplication)
                EngLearningViewModel(application.database.contentsDao())
            }
        }
    }
}