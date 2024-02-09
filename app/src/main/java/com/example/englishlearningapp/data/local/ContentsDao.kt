package com.example.englishlearningapp.data.local

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ContentsDao {
    @Query(
        """
        SELECT * FROM Chapter_material_1
        ORDER BY id ASC    
        """
    )
    fun getAll(): Flow<List<Content>>

    @Query(
        """
        SELECT * FROM Chapter_material_1
        WHERE passed = 1
        ORDER BY id ASC 
        """
    )
    fun getOnlyPassed(): Flow<List<Content>>

    @Query(
        """
        SELECT * FROM Practice
        ORDER BY id ASC    
        """
    )
    fun getAllSecondTable(): Flow<List<Content2>>

//    @Query(
//        """
//        SELECT * FROM Practice
//        WHERE value = :stopName
//        ORDER BY id ASC
//        """
//    )
//    fun getByIndex(stopName: String): Flow<List<Content>>
}