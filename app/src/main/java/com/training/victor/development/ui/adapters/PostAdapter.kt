package com.training.victor.development.ui.adapters

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.training.victor.development.R
import com.training.victor.development.data.models.PostViewModel
import com.training.victor.development.utils.inflate
import kotlinx.android.synthetic.main.adapter_profile_item.view.*

class PostAdapter(private val profilesList: ArrayList<PostViewModel>,
                  private val onPostClickListener: OnPostClickListener): RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CreatorViewHolder(parent.inflate(R.layout.adapter_profile_item))
    }

    override fun getItemCount(): Int {
        return profilesList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is CreatorViewHolder) {
            holder.bind(profilesList[position], onPostClickListener)
        }
    }

    class CreatorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(post: PostViewModel, onPostClickListener: OnPostClickListener) = with(itemView) {
            txtTitle.text = post.title
            txtBody.text = post.body

            itemView.setOnClickListener {
                onPostClickListener.onPostClicked(post)
            }
        }
    }

    interface OnPostClickListener {
        fun onPostClicked(post: PostViewModel)
    }
}