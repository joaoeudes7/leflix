package com.jedev.leflix.view

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.jedev.leflix.R
import com.jedev.leflix.adapter.BookAdapter
import com.jedev.leflix.model.Book
import com.jedev.leflix.service.api.GoogleBookService
import com.jedev.leflix.service.api.entities.Volume
import kotlinx.android.synthetic.main.activity_results_books.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ResultsBooksActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_results_books)

        setupRecyclerView()

        intent.extras?.getString("query")?.let { query ->
                title = "Resultados de '$query''"

                GoogleBookService().searchBook(query, 20).enqueue(object : Callback<Volume?> {
                    override fun onResponse(call: Call<Volume?>, response: Response<Volume?>) {
                        response.body()?.let {
                            val data = it.items.map { i -> Book().applyData(i) }
                            recyclerView.adapter = BookAdapter(data)
                        }
                    }

                    override fun onFailure(call: Call<Volume?>, t: Throwable) {
                        Log.d("ERROR GOOGLE BOOK API:", t.message)
                        Toast.makeText(this@ResultsBooksActivity, "Falha ao consultar dados", Toast.LENGTH_SHORT).show()
                    }
                })
        }
    }


    private fun setupRecyclerView() {
        // Set Recycle Layout
        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        recyclerView.setItemViewCacheSize(20)
    }
}
