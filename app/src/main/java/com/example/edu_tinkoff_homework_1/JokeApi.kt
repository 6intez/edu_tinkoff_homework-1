package com.example.edu_tinkoff_homework_1
import com.example.edu_tinkoff_homework_1.data.JokeResponse
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query

interface JokeApi {
    @GET("joke/Any")
    suspend fun getJokes(
        @Query("blacklistFlags") blacklistFlags: String = "nsfw,religious,political,racist,sexist,explicit",
        @Query("type") type: String = "twopart",
        @Query("amount") amount: Int = 10
    ): JokeResponse
}

object RetrofitInstance {
    private const val BASE_URL = "https://v2.jokeapi.dev/"

    val api: JokeApi by lazy {

        val loggingInterceptor = HttpLoggingInterceptor().apply{
            level = HttpLoggingInterceptor.Level.BODY
        }
        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(retrofit2.converter.gson.GsonConverterFactory.create())
            .build()
            .create(JokeApi::class.java)
    }
}