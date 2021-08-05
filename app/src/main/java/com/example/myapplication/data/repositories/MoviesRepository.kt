package net.simplifiedcoding.data.repositories

import com.example.gotrypper_paging.model.New
import com.example.myapplication.data.repositories.NewsDatabase
import net.simplifiedcoding.data.network.MoviesApi

class MoviesRepository(
    private val db: NewsDatabase
) : SafeApiRequest() {

    suspend fun getNews(page:Int) = apiRequest { MoviesApi().getMovies(page) }

    suspend fun insertNews(data: New) = db.getNewsDao().savedData(data)

    suspend fun getRoomNews() = db.getNewsDao().getSavedNews()

    suspend fun getSavedNews() = db.getNewsDao().getAllNews()

    suspend fun deleteRoom(data:String) = db.getNewsDao().deleteNews(data)

}