package com.jedev.leflix.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jedev.leflix.R
import com.jedev.leflix.model.Comment
import kotlinx.android.synthetic.main.simple_book_item.view.*
import kotlinx.android.synthetic.main.simple_comment.view.*

class CommentAdapter(private var items: List<Comment>): RecyclerView.Adapter<CommentAdapter.CustomViewHolder>() {
    lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        context = parent.context
        val layoutInflater = LayoutInflater.from(context)
        val itemView = layoutInflater.inflate(R.layout.simple_comment, parent, false)

        return CustomViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val item = items[position]

        holder.bind(item)
    }

    class CustomViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val txtUser = view.txt_title!!
        private val avatarUser = view.avatar_user
        private val txtContent = view.txt_content!!
        private val txtCreated = view.txt_created!!

        fun bind(item: Comment) {
            item.user?.let {
                txtUser.text = it.name

                if (!it.photoUrl.isNullOrEmpty()) {
                    Glide
                            .with(itemView)
                            .load(it.photoUrl)
                            .into(avatarUser)
                }
            }

            txtContent.text = item.content
            txtCreated.text = item.updatedOn.toString()
        }
    }
}