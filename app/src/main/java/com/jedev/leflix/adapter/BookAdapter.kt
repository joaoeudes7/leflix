package com.jedev.leflix.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.jedev.leflix.R
import com.jedev.leflix.model.Book
import com.jedev.leflix.view.BookDetailsActivity
import kotlinx.android.synthetic.main.simple_book_item.view.*

class BookAdapter(private var items: List<Book>) : androidx.recyclerview.widget.RecyclerView.Adapter<BookAdapter.CustomViewHolder>() {
    lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        context = parent.context
        val layoutInflater = LayoutInflater.from(context)
        val itemView = layoutInflater.inflate(R.layout.simple_book_item, parent, false)

        return CustomViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val report = items[position]

        holder.bind(report)
        holder.itemView.setOnClickListener {
            goToDetails(report)
        }
    }

    private fun goToDetails(item: Book) {
        val intentDetails = Intent(context, BookDetailsActivity::class.java)

        intentDetails.putExtra("bookJson", Gson().toJson(item))
        context.startActivity(intentDetails)
    }

    class CustomViewHolder(itemView: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView) {

        private val thumbnail: ImageView = itemView.cover_book!!
        private val title: TextView = itemView.txt_title!!
        private val subtitle: TextView = itemView.txt_subtitle!!

        fun bind(book: Book) {
            book.thumbnails?.smallThumbnail?.let {
                Glide
                        .with(itemView.context)
                        .load(it)
                        .into(this.thumbnail)

            }

            title.text = book.title
            subtitle.text = book.subtitle
        }
    }
}