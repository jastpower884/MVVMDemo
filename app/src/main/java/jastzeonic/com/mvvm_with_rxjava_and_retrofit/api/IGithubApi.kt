package jastzeonic.com.mvvm_with_rxjava_and_retrofit.api

import retrofit2.Call
import retrofit2.http.GET

interface IGithubApi {

    @GET("/")
    fun getGitHubApi(): Call<Map<String, String>>

}