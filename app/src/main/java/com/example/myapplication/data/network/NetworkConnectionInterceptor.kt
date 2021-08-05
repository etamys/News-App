package com.example.myapplication.data.network

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.example.myapplication.util.NoInternetException
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException


class NetworkConnectionIntercepto(context: Context):Interceptor {

    private val applicationContext = context.applicationContext

    override fun intercept(chain: Interceptor.Chain): Response {
        if (!isInternetAvailAble()){
            try {
                throw NoInternetException("Make sure you have an active data connection")
            }catch (e:NoInternetException)
            {

            }
        }
        return chain.proceed(chain.request())

    }

    @SuppressLint("NewApi")
    fun isInternetAvailAble(): Boolean {
        var result = false
        val connectivityManager =
            applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        connectivityManager?.let {
            it.getNetworkCapabilities(connectivityManager.activeNetwork)?.apply {
                result = when {
                    hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                    hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                    else -> false
                }
            }
        }
        return result
    }
}
