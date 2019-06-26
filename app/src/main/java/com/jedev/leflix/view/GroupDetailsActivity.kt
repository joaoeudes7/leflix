package com.jedev.leflix.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jedev.leflix.R

class GroupDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_group_details)

        intent.extras?.getString("groupId")
    }
}
