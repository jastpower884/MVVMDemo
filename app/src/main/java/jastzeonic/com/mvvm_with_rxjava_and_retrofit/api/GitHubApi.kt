package jastzeonic.com.mvvm_with_rxjava_and_retrofit.api

import android.support.annotation.WorkerThread
import com.google.gson.GsonBuilder
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.FlowableOnSubscribe
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import javax.xml.datatype.DatatypeConstants.SECONDS
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit


class GitHubApi {

    @WorkerThread
    private fun requestGitHubApi(): Response<Map<String, String>>? {

//        val loggingInterceptor = HttpLoggingInterceptor()
//        loggingInterceptor.setLevel(if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE)

        val okHttpClient = OkHttpClient.Builder()
                .readTimeout(1, TimeUnit.MILLISECONDS)
                .writeTimeout(1, TimeUnit.MILLISECONDS)
                .connectTimeout(1, TimeUnit.MILLISECONDS)
                .build()


        val gson = GsonBuilder()
        val retrofit = Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://api.github.com")
                .addConverterFactory(GsonConverterFactory.create(gson.create()))

                .build()

        val request = retrofit.create(IGithubApi::class.java)


        return try {
            request.getGitHubApi().execute()

        } catch (type: Exception) {
            type.printStackTrace()
            null
        }

    }


    fun getGitHubApi(): Flowable<Response<Map<String, String>>> {

        Flowable.just("test")
                .map({})

        return Flowable.create({
            val result = requestGitHubApi()
            if (result != null) {
                it.onNext(result)
            } else {
                it.onError(Throwable(" file not find"))
            }
            it.onComplete()
        }, BackpressureStrategy.BUFFER)

    }

}