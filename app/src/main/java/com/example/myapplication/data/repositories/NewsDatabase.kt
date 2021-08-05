package com.example.myapplication.data.repositories

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.gotrypper_paging.model.New
import com.example.myapplication.data.models.NewsDao


@Database(
    entities = [New::class],
    version = 1,
    exportSchema = false
)
abstract class NewsDatabase : RoomDatabase(){

    abstract fun getNewsDao() : NewsDao

    companion object {

        @Volatile private var instance : NewsDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            NewsDatabase::class.java,
            "NewsData"
        ).build()

    }
}