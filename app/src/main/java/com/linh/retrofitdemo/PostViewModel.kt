package com.linh.retrofitdemo

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PostViewModel : ViewModel() {
    val logging = HttpLoggingInterceptor().apply {
        setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    val client = OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()

    val retrofit = Retrofit.Builder()
        .baseUrl("https://jsonplaceholder.typicode.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    val service = retrofit.create(PostService::class.java)

    fun getPost() {
        viewModelScope.launch {
            val posts = service.getPost()
            Log.d("PostViewModel", "PostVM")
            Log.d("PostViewModel", posts.toString())
        }
    }

    fun createPost() {
        viewModelScope.launch {
            service.createPost(Post(1, 0, "foo", "bar"))
        }
    }
}