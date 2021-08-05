package net.simplifiedcoding.data.network


import com.example.gotrypper_paging.model.News
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface MoviesApi {

    @FormUrlEncoded
    @POST("LGLNews")
    suspend fun getMovies(
        @Field("page") page: Int
    ) : Response<News>



    companion object{
        operator fun invoke(
        ) : MoviesApi {
            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://news.soolegal.com/api/index.php/")
                .build()
                .create(MoviesApi::class.java)
        }
    }
}
