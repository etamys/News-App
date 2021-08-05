package com.example.myapplication.data.models

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.gotrypper_paging.model.New

@Dao
interface NewsDao {

    @Insert()
    fun savedData(data: New)


    @Query("SELECT * FROM  NewsData")
    fun getAllNews():LiveData<List<New>>

    @Query("SELECT * FROM NewsData")
    fun getSavedNews():List<New>


    @Query("UPDATE NewsData SET Status = :data WHERE id = :id")
    fun updateNote(data: Boolean,id:Int)

    @Delete
    suspend fun deleteRoomNews(data: New)

    @Query("DELETE FROM NewsData WHERE news_id = :id")
    suspend fun deleteNews(id: String):Int

    @Query("SELECT * FROM NewsData WHERE news_id=:id")
     fun getResult(id:String):Boolean
}