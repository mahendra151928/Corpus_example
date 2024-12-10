package com.example.myapplication

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso


class ContentAdapter(private val context: Context, private val contentList: List<Content>) :
    RecyclerView.Adapter<ContentAdapter.ContentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_content, parent, false)
        return ContentViewHolder(view)
    }

    override fun onBindViewHolder(holder: ContentViewHolder, position: Int) {
        val content = contentList[position]
        holder.bind(content)
    }

    override fun getItemCount(): Int = contentList.size

    class ContentViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val titleTextView: TextView = view.findViewById(R.id.titleTextView)
        private val horizontalRecyclerView: RecyclerView = view.findViewById(R.id.horizontalRecyclerView)

        fun bind(content: Content) {
            titleTextView.text = content.title
            horizontalRecyclerView.layoutManager =
                LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
            horizontalRecyclerView.adapter = CarouselAdapter(content.content)
        }
    }

    class CarouselAdapter(private val contentItems: List<ContentItem>) :
        RecyclerView.Adapter<CarouselAdapter.CarouselViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarouselViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_carousel, parent, false)
            return CarouselViewHolder(view)
        }

        override fun onBindViewHolder(holder: CarouselViewHolder, position: Int) {
            val item = contentItems[position]
            holder.bind(item)
        }

        override fun getItemCount(): Int = contentItems.size

        class CarouselViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            private val imageView: ImageView = view.findViewById(R.id.imageView)
            private val idTextView: TextView = view.findViewById(R.id.idTextView)

            fun bind(contentItem: ContentItem) {
                idTextView.text = contentItem.id.toString()

                if (!contentItem.mobileCarouselImage.isNullOrEmpty()) {
                    Glide.with(itemView.context)
                        .load(contentItem.mobileCarouselImage)
                        .placeholder(R.drawable.ic_launcher_background)
                        .into(imageView)
                    imageView.visibility = View.VISIBLE
                } else {
                    imageView.visibility = View.GONE
                }
            }
        }
    }
}