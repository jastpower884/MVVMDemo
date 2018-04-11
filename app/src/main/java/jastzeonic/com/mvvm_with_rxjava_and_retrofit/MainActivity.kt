package jastzeonic.com.mvvm_with_rxjava_and_retrofit

import android.arch.lifecycle.Observer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.UiThread
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.widget.LinearLayoutManager
import jastzeonic.com.mvvm_with_rxjava_and_retrofit.vm.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

@UiThread
class MainActivity : AppCompatActivity() {


    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)




        mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        mainViewModel.response.observe(this, Observer<Map<String, String>> { result ->
            recycler_view.layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
            if (result != null) {
                val keys: MutableList<String> = mutableListOf()
                keys.addAll(result.keys)

                recycler_view.adapter = ListAdatper(keys, result)
            }
        })


    }


    override fun onStart() {
        super.onStart()
        mainViewModel.loadGitHubApi()
    }

    override fun onStop() {
        super.onStop()
        mainViewModel.clear()
    }
}
