package com.jedev.leflix.view

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.jedev.leflix.R
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.app_bar_home.*
import kotlinx.android.synthetic.main.content_home.*


class HomeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private val auth: FirebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }
    val user = FirebaseAuth.getInstance().currentUser
    lateinit var name: String
    lateinit var email: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(toolbar)




        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        val toggle = ActionBarDrawerToggle(this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        setupNav()
        setupRecycleView()
    }

    override fun onBackPressed() {
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.home, menu)

        val searchItem = menu.findItem(R.id.action_search)
        // Optional: if you want to expand SearchView from icon to edittext view
        searchItem.expandActionView()

        val searchView = searchItem.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String): Boolean {
                return true
            }
            override fun onQueryTextSubmit(query: String): Boolean {
                val intent = Intent(this@HomeActivity, ResultsBooks::class.java)

                intent.putExtra("query", query)
                startActivity(intent)
                return true
            }
        })

        return true
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.nav_inicio -> {
                // chama activity inicio
            }
            R.id.nav_grupos -> {
                startActivity(Intent(this, GroupsActivity::class.java))
            }
            R.id.nav_em_alta -> {
                // chama activity em alta
            }
            R.id.nav_historico -> {
                // chama activity historico
            }
            R.id.nav_meu_perfil -> {
                // chama activity meu perfil
            }
            R.id.nav_sair -> {
                // faz logoff
                auth.signOut()
                startActivity(Intent(this, LoginActivity::class.java))
                finish()

            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }


    private fun setupNav() {
        if (user != null) {
            val navigationView = findViewById<NavigationView>(R.id.nav_view)
            val headerView = navigationView.getHeaderView(0)
            val title = headerView.findViewById<androidx.appcompat.widget.AppCompatTextView>(R.id.nav_user)
            val email = headerView.findViewById<androidx.appcompat.widget.AppCompatTextView>(R.id.nav_user_email)
            val nomeusuario = user.displayName.toString()
            val emailusuario = user.email.toString()
            title.text = nomeusuario
            email.text = emailusuario

            nav_view.setNavigationItemSelectedListener(this)
        }
    }

    private fun setupRecycleView() {
        // Set Recycle Layout
        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        recyclerView.setItemViewCacheSize(20)
    }
}
