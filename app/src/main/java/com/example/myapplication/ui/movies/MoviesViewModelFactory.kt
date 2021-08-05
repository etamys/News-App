package com.example.myapplication.ui.movies

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import net.simplifiedcoding.data.repositories.MoviesRepository

@Suppress("UNCHECKED_CAST")
class MoviesViewModelFactory(
    val app: Application,
    private val apiHelper: MoviesRepository
) : ViewModelProvider.NewInstanceFactory(){

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ParallelNetworkCallsViewModel::class.java)) {
            return ParallelNetworkCallsViewModel(apiHelper,app) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}