package jastzeonic.com.mvvm_with_rxjava_and_retrofit.api

import android.support.annotation.WorkerThread
import com.google.gson.GsonBuilder
import io.reactivex.Flowable
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

class GitHubApi {

    @WorkerThread
    private fun requestGitHubApi(): Response<Map<String, String>> {
        val gson = GsonBuilder()
        val retrofit = Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .addConverterFactory(GsonConverterFactory.create(gson.create()))
                .build()

        val request = retrofit.create(IGithubApi::class.java)
        return request.getGitHubApi().execute()
    }


    fun getGitHubApi(): Flowable<Response<Map<String, String>>> {
        return Flowable.fromCallable({ requestGitHubApi() })

    }

}