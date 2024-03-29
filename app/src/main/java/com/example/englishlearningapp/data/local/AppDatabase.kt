package com.example.englishlearningapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Content::class, Content2::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun contentsDao(): ContentsDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "app_database"
                )
                    .createFromAsset("database/EngLearningAppDB.db")
                    // Wipes and rebuilds instead of migrating if no Migration object.
                    // Migration is not part of this code lab.
                    .fallbackToDestructiveMigration()
                    .build()
                    .also {
                        INSTANCE = it
                    }
            }
        }
    }
}