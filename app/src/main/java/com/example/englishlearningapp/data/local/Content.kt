package com.example.englishlearningapp.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Chapter_material_1")
data class Content(
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "value")
    val value: String,
    @ColumnInfo(name = "index")
    val index: String,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "passed")
    val passed: Boolean
)

@Entity(tableName = "Practice")
data class Content2(
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "task")
    val task: String,
    @ColumnInfo(name = "material")
    val material: String,
    @ColumnInfo(name = "answer")
    val answer: String,
    @ColumnInfo(name = "theme")
    val theme: Int,
    @ColumnInfo(name = "subTheme")
    val subTheme: Int,
)