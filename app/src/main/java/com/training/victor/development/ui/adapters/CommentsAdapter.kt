package com.training.victor.development.ui.adapters

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.training.victor.development.R
import com.training.victor.development.data.models.CommentViewModel
import com.training.victor.development.utils.inflate
import kotlinx.android.synthetic.main.adapter_comment_item.view.*

class CommentsAdapter(private val commentsList: ArrayList<CommentViewModel>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CreatorViewHolder(parent.inflate(R.layout.adapter_comment_item))
    }

    override fun getItemCount(): Int {
        return commentsList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is CreatorViewHolder) {
            holder.bind(commentsList[position])
        }
    }

    class CreatorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(comment: CommentViewModel) = with(itemView) {
            txtName.text = comment.name
            txtEmail.text = comment.email
            txtCommentBody.text = comment.body
        }
    }
}