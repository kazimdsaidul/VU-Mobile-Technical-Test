package com.vu.mobile.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kazi.test.ui.employeesList.ImagesListFragment
import com.vu.mobile.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragmentContainer, ImagesListFragment())
                .commit()
        }
    }
}
