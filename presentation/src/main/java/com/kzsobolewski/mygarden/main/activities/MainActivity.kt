package com.kzsobolewski.mygarden.main.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.kzsobolewski.mygarden.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        setSupportActionBar(main_toolbar as Toolbar)
//        supportActionBar?.setDisplayShowTitleEnabled(false)
    }
}