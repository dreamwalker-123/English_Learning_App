package com.example.englishlearningapp

import android.app.Application
import android.content.Context
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
// Контекст является родителем Activity и Application, поэтому в этих класах
// можно будет при помощи этого расширения обращаться к полю appComponent
val Context.appComponent: AppComponent
    get() = when (this) {
        is EngLearningApplication -> appComponent
        else -> applicationContext.appComponent
    }