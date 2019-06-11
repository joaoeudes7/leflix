package com.jedev.leflix.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.jedev.leflix.R
import com.jedev.leflix.model.Group
import com.jedev.leflix.view.GroupDetailsActivity
import kotlinx.android.synthetic.main.simple_book_item.view.*

class GroupAdapter(private var reports: List<Group>) : androidx.recyclerview.widget.RecyclerView.Adapter<GroupAdapter.CustomViewHolder>() {

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

        holder.card.setOnClickListener {
            goToDetails(report)
        }
    }

    private fun goToDetails(item: Group) {
        val intentDetails = Intent(context, GroupDetailsActivity::class.java)
        intentDetails.putExtra("id", item.id)
        context.startActivity(intentDetails)
    }

    class CustomViewHolder(itemView: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView) {

        val card: CardView = itemView.cardview
        private val thumbnail: ImageView = itemView.imageView!!
        private val title: TextView = itemView.title!!
        private val descriptionView: TextView = itemView.subtitle!!
    }
}