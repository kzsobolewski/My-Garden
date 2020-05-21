package com.kzsobolewski.mygarden.main.activities

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.kzsobolewski.domain.models.Plant
import com.kzsobolewski.domain.models.PlantsResponse
import com.kzsobolewski.mygarden.R
import com.kzsobolewski.mygarden.databinding.ActivityMainBinding
import com.kzsobolewski.mygarden.main.fragments.INavigationFragment
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    //TODO
    // use case
    // polish language
    // dark theme

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.isToolbarImageVisible = true
        setSupportActionBar(main_toolbar as Toolbar)
        supportActionBar?.apply {
            setDisplayShowTitleEnabled(true)
        }
    }

    override fun onStart() {
        super.onStart()
        listExample()

        tmpButton.setOnClickListener {
            apiCall()
                .subscribeOn(Schedulers.io())
                .subscribe { result: PlantsResponse ->
                    Log.d("MainActivity", result.keys.joinToString(",") { it })
                }
        }
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.settings_option -> {
                Navigation.findNavController(this, R.id.nav_host_fragment)
                    .navigate(R.id.action_tabsFragment_to_settingsFragment)
                true
            }
            R.id.about_option -> {
                Navigation.findNavController(this, R.id.nav_host_fragment)
                    .navigate(R.id.action_tabsFragment_to_aboutFragment)
                true
            }
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else ->
                super.onOptionsItemSelected(item)
        }
    }


    override fun onBackPressed() {
        val handled =
            (supportFragmentManager
                .primaryNavigationFragment
                ?.childFragmentManager
                ?.primaryNavigationFragment as? INavigationFragment)?.onBackPressed() ?: false
        if (!handled)
            super.onBackPressed()
    }

    fun setUpNavigationVisibility(value: Boolean) {
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(value)
            setDisplayShowHomeEnabled(value)
        }
    }

    fun setLogoVisibility(value: Boolean) {
        binding.isToolbarImageVisible = value
    }

    fun setToolbarForSideScreen(title: String?) {
        setUpNavigationVisibility(true)
        setLogoVisibility(false)
        this.title = title ?: ""
    }

    fun apiCall(): Single<PlantsResponse>{
        return Single.create { emitter ->
            emitter.onSuccess(
                mapOf(
                    "lala" to Plant(id = "1"),
                    "lala2" to Plant(id = "2")
                )
            )
        }
    }

    val list = listOf<Int>(1, 2, 3, 4, 5, 6, 7, 8, 9)
    val mySingle/*: Single<Int>*/ = Single.create<List<Int>> { emitter ->
        emitter.onSuccess(list)
        //emitter.tryOnError(NullPointerException())
    }
        .map { list ->
            list.filter { it % 2 == 0 }
        }
        .map { list ->
            list.first()
        }

    fun listExample() {
        mySingle
            .observeOn(Schedulers.computation())
            .subscribeOn(Schedulers.io())
            .map {
                it * 2
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->
                    Log.i("MainActivity", "value: $result")
                },
                { error ->
                    Log.i("MainActivity", "value: $error")
                }
            )
    }
}