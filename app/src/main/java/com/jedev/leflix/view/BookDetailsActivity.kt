package com.jedev.leflix.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.jedev.leflix.R
import com.jedev.leflix.adapter.CommentAdapter
import com.jedev.leflix.model.Book
import com.jedev.leflix.service.api.GoogleBookService
import com.jedev.leflix.service.api.entities.Item
import com.jedev.leflix.service.api.entities.Volume
import com.jedev.leflix.service.firebase.BookService
import kotlinx.android.synthetic.main.activity_book_details.*
import retrofit2.Call
import retrofit2.Response

class BookDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_details)

        setupRecyclerView()

        intent.extras?.let {
            val bookJson = it.getString("bookJson")
            val book = Gson().fromJson(bookJson, Book::class.java)

            bindBook(book)
        }


        btn_add_to_library.setOnClickListener {
            val intent = Intent(this, NewReadingActivity::class.java)

            startActivity(intent)
        }
    }

    private fun bindBook(book: Book) {
        book.thumbnails?.let {
            Glide
                    .with(this)
                    .load(it.thumbnail)
                    .into(cover_book)
        }

        txt_title.text = book.title
        txt_subtitle.text = book.subtitle
        txt_description.text = book.synopsis

        loadComments(book.id!!)
    }

    private fun loadComments(idBook: String) {
        BookService().getById(idBook).addOnSuccessListener { doc ->
            if (doc.exists()) {
                doc.toObject(Book::class.java)?.comments?.let { comments ->
                    recyclerView_comments.adapter = CommentAdapter(comments)
                    txt_counter_comments.text = comments.size.toString()
                }
            }
        }
    }

    private fun setupRecyclerView() {
        // Set Recycle Layout
        recyclerView_comments.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        recyclerView_comments.setHasFixedSize(true)
        recyclerView_comments.setItemViewCacheSize(20)
    }
}