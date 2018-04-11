package jastzeonic.com.mvvm_with_rxjava_and_retrofit.vm

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import jastzeonic.com.mvvm_with_rxjava_and_retrofit.api.GitHubApi

class MainViewModel(application: Application) : AndroidViewModel(application) {


    val response = MutableLiveData<Map<String, String>>()
    val disposable = CompositeDisposable()

    fun clear(){
        disposable.clear()
    }


    fun loadGitHubApi() {
        val api = GitHubApi()

        disposable.add(api.getGitHubApi()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ onNextResponse -> response.value = onNextResponse.body() }, { onError -> onError }))

    }

}