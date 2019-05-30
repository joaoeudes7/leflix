package com.jedev.leflix.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.bumptech.glide.Glide
import com.jedev.leflix.R
import com.jedev.leflix.service.api.entities.Item
import com.jedev.leflix.view.BookDetails
import kotlinx.android.synthetic.main.simple_book_item.view.*

class BookAdapter(private var reports: List<Item>) : androidx.recyclerview.widget.RecyclerView.Adapter<BookAdapter.CustomViewHolder>() {
    lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        context = parent.context
        val layoutInflater = LayoutInflater.from(context)
        val itemView = layoutInflater.inflate(R.layout.simple_book_item, parent, false)

        return CustomViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return reports.size
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val report = reports[position]

        holder.bind(report)
        holder.card.setOnClickListener {
            goToDetails(report)
        }
    }

    private fun goToDetails(item: Item) {
        val intentDetails = Intent(context, BookDetails::class.java)
        intentDetails.putExtra("id", item.id)
        context.startActivity(intentDetails)
    }

    class CustomViewHolder(itemView: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView) {

        val card: CardView = itemView.cardview
        private val thumbnail: ImageView = itemView.imageView!!
        private val title: TextView = itemView.title!!
        private val descriptionView: TextView = itemView.subtitle!!

        fun bind(item: Item) {
            var thumbUrl = item.volumeInfo.imageLinks.smallThumbnail
            if (thumbUrl.startsWith("http://"))
                thumbUrl = thumbUrl.replace("http://", "https://")

            Glide
                    .with(itemView.context)
                    .load(thumbUrl)
                    .into(thumbnail)

            title.text = item.volumeInfo.title
            descriptionView.text = item.volumeInfo.subtitle
        }
    }
}