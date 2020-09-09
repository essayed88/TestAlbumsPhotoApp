package com.test.albums.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.test.albums.R
import com.test.albums.data.db.entities.Photo


class PhotosAdapter : RecyclerView.Adapter<PhotosAdapter.CustomViewHolder>() {
    private var dataItems = mutableListOf<Photo>()

    fun setData(photosList: MutableList<Photo>) {
        dataItems.clear()
        dataItems = photosList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PhotosAdapter.CustomViewHolder {
        val v =
            LayoutInflater.from(parent.context).inflate(R.layout.photo_item_layout, parent, false)
        return CustomViewHolder(v)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val photo: Photo = dataItems[position]
        holder.tvPhotoTitle.text = photo.title
        Picasso.get().load(photo.thumbnailUrl).into(holder.ivPhoto)
    }

    override fun getItemCount(): Int {
        return dataItems.size
    }

    inner class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var ivPhoto: ImageView = itemView.findViewById(R.id.ivPhoto) as ImageView
        var tvPhotoTitle: TextView = itemView.findViewById(R.id.tvPhotoTitle)

    }
}