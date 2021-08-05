package com.example.myapplication.ui.movies

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.*
import com.example.gotrypper_paging.model.New
import com.example.gotrypper_paging.model.News
import com.example.myapplication.data.repositories.NewsDatabase
import com.example.myapplication.util.Resource
import kotlinx.coroutines.*
import net.simplifiedcoding.data.network.MoviesApi
import net.simplifiedcoding.data.repositories.MoviesRepository

class ParallelNetworkCallsViewModel(
    private val apiHelper: MoviesRepository, app: Application
) : AndroidViewModel(app) {


    val news = MutableLiveData<Resource<News>>()
    val news1 = MutableLiveData<News>()
    lateinit var data: LiveData<List<New>>
    lateinit var data1: List<New>


    init {
        fetchUsers()
        getSavedNews()
        roomNews()

    }

    fun fetchUsers() {
        viewModelScope.launch(Dispatchers.Default) {
            news.postValue(Resource.loading(null))
            try {
                val response = apiHelper.getNews(1)
                news.postValue(Resource.success(response))
                news1.postValue(response)
            } catch (e: Exception) {
                news.postValue(Resource.error("Something Went Wrong", null))
            }
        }
    }

    fun SaveNews(data: New) = viewModelScope.launch(Dispatchers.Default) {
        apiHelper.insertNews(data)
    }

    fun getSavedNews() = viewModelScope.launch(Dispatchers.Default) {
        data = apiHelper.getSavedNews()
    }

    fun roomNews() = viewModelScope.launch(Dispatchers.Default) {
        data1 = apiHelper.getRoomNews()
    }


    fun deleteArticle(data:String) = viewModelScope.launch{
        apiHelper.deleteRoom(data)
    }


}
