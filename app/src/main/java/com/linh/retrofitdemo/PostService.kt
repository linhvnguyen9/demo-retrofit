package com.linh.retrofitdemo

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface PostService {
    @GET("posts")
    suspend fun getPost(): List<Post>

    @POST("posts")
    suspend fun createPost(@Body post: Post)
}