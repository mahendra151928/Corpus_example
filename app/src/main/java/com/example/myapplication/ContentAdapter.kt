
package com.example.myapplication

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
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
        holder.bind(content) // Pass content to the view holder
    }

    override fun getItemCount(): Int = contentList.size

    class ContentViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val titleTextView: TextView = view.findViewById(R.id.titleTextView)
        private val horizontalRecyclerView: RecyclerView = view.findViewById(R.id.horizontalRecyclerView)

        fun bind(content: Content) {
            if(content.contentType == "CAROUSEL_AD"){
                titleTextView.visibility = View.GONE
            }else{
                titleTextView.text = content.title
                titleTextView.visibility = View.VISIBLE
            }

            horizontalRecyclerView.layoutManager =
                LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)

            // Pass contentType correctly to CarouselAdapter
            horizontalRecyclerView.adapter = CarouselAdapter(content.content, content.contentType)
        }
    }
}

class CarouselAdapter(private val contentItems: List<ContentItem>, private val parentContentType: String) :
    RecyclerView.Adapter<CarouselAdapter.CarouselViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarouselViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_carousel, parent, false)
        return CarouselViewHolder(view)
    }

    override fun onBindViewHolder(holder: CarouselViewHolder, position: Int) {
        val item = contentItems[position]
        holder.bind(item,parentContentType)
    }

    override fun getItemCount(): Int = contentItems.size

    class CarouselViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val imageView: ImageView = view.findViewById(R.id.imageView)
        private val idTextView: TextView = view.findViewById(R.id.idTextView)
        private val cardView: CardView = view.findViewById(R.id.cardView)

        fun bind(contentItem: ContentItem, parentContentType: String) {


            // Check the contentType from the parent (e.g., "CAROUSEL_AD")
            if (!contentItem.mobileCarouselImage.isNullOrEmpty()) {
                Glide.with(itemView.context)
                    .load(contentItem.mobileCarouselImage)
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(imageView)

                // Adjust image layout based on parent contentType
                when (parentContentType) {
                    "CAROUSEL_AD" -> {
                        // Full image for CAROUSEL_AD (TopBanner)
                        imageView.layoutParams.width = ConstraintLayout.LayoutParams.MATCH_PARENT
                        imageView.layoutParams.height = 500 // Adjust this height as needed
                        cardView.layoutParams.height = ConstraintLayout.LayoutParams.WRAP_CONTENT
                        cardView.layoutParams.width = ConstraintLayout.LayoutParams.MATCH_PARENT
                        idTextView.text = contentItem.id.toString()
                        idTextView.visibility = View.GONE
                    }
                    else -> {
                        // Wrapped image for other types (fixed width and height)
                        imageView.layoutParams.width = 120.dpToPx(itemView.context)
                        imageView.layoutParams.height = 100.dpToPx(itemView.context)
                        imageView.scaleType = ImageView.ScaleType.CENTER_CROP
                        cardView.layoutParams.height = ConstraintLayout.LayoutParams.WRAP_CONTENT
                        cardView.layoutParams.width = ConstraintLayout.LayoutParams.WRAP_CONTENT
                        idTextView.text = contentItem.id.toString()
                        idTextView.visibility = View.GONE
                    }
                }

                imageView.requestLayout()
            } else {
                imageView.visibility = View.GONE
                cardView.layoutParams.height = ConstraintLayout.LayoutParams.WRAP_CONTENT
                cardView.layoutParams.width =  50.dpToPx(itemView.context)
                idTextView.text = contentItem.id.toString()
                idTextView.visibility = View.VISIBLE
            }
        }

        // Extension function to convert dp to px
        fun Int.dpToPx(context: Context): Int {
            return (this * context.resources.displayMetrics.density).toInt()
        }
    }
}
