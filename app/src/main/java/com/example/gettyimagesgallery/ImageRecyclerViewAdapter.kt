package com.example.gettyimagesgallery

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ImageRecyclerViewAdapter :
    RecyclerView.Adapter<ImageRecyclerViewAdapter.ViewHolder>() {

    private var imageUrls: ArrayList<String> = ArrayList()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val imageView: ImageView = view.findViewById(R.id.imageView)

        fun bind(bitmap: String) {
            Glide.with(imageView)
                .load(bitmap)
                .thumbnail(0.33f)
                .centerCrop()
                .into(imageView)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.image_layout, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(imageUrls.get(position))
    }

    override fun getItemCount() = imageUrls.size

    fun updateData(imageUrls: ArrayList<String>) {
        this.imageUrls = imageUrls
        notifyDataSetChanged()
    }
}
