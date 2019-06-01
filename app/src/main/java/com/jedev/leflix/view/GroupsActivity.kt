package com.jedev.leflix.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jedev.leflix.R
import kotlinx.android.synthetic.main.activity_grupos.*
import kotlinx.android.synthetic.main.content_grupos.*

class GroupsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grupos)
        setSupportActionBar(toolbar)

        fab.setOnClickListener {
            val intent = Intent(this, AddGrupoActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setupRecycleView() {
        // Set Recycle Layout
        recycler_grupos.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        recycler_grupos.setHasFixedSize(true)
        recycler_grupos.setItemViewCacheSize(20)
    }

}
