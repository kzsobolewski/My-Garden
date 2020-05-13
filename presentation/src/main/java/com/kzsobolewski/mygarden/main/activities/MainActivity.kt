package com.kzsobolewski.mygarden.main.activities

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import com.kzsobolewski.mygarden.R
import com.kzsobolewski.mygarden.databinding.ActivityMainBinding
import com.kzsobolewski.mygarden.main.fragments.INavigationFragment
import com.kzsobolewski.mygarden.search.viewmodels.SearchViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val searchViewModel by viewModel<SearchViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.isToolbarImageVisible = true
        setSupportActionBar(main_toolbar as Toolbar)
        supportActionBar?.apply {
            setDisplayShowTitleEnabled(false)
        }
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        handleSearchView(item)
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else ->
                super.onOptionsItemSelected(item)
        }
    }


    private fun handleSearchView(item: MenuItem) {
        val searchView = item.actionView as? SearchView
        searchView?.apply {
            queryHint = "Write plant name"
            setOnQueryTextListener(searchViewListener)
        }
    }

    private val searchViewListener = object : SearchView.OnQueryTextListener {

        override fun onQueryTextSubmit(query: String?): Boolean {
            Log.d("search this", query)
            if (query != null)
                searchViewModel.loadPlants(query)
            return false
        }

        override fun onQueryTextChange(newText: String?): Boolean {
            Log.d("search this", newText)
            if (newText != null && newText.length >= 3)
                searchViewModel.loadPlants(newText)
            return false
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


    fun setSearchButtonVisibility(value: Boolean) {
        findViewById<ActionMenuItemView>(R.id.search_button)?.visibility =
            if (value) View.VISIBLE else View.GONE
    }
}