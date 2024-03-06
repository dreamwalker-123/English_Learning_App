package com.example.englishlearningapp

import android.app.Application
import com.example.englishlearningapp.data.dagger.AppComponent
import com.example.englishlearningapp.data.dagger.DaggerAppComponent
import com.example.englishlearningapp.data.local.AppDatabase

class EngLearningApplication: Application() {
    val database: AppDatabase by lazy { AppDatabase.getDatabase(this) }

    // все что ниже тренировка вставки даггера
    lateinit var appComponent: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.create()
    }
}