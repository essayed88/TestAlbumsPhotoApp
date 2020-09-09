package com.test.albums.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.test.albums.R
import com.test.albums.data.db.entities.Album


class AlbumsAdapter : RecyclerView.Adapter<AlbumsAdapter.ViewHolder>() {

    private var dataItems = mutableListOf<Album>()
    private lateinit var context: Context

    fun setData(context: Context, albumsList: MutableList<Album>) {
        dataItems.clear()
        dataItems = albumsList
        this.context = context
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v =
            LayoutInflater.from(parent.context).inflate(R.layout.album_item_layout, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.recyclerView.adapter = PhotosAdapter().apply {
            setData(dataItems[position].photosList)
        }
        holder.recyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        holder.recyclerView.setHasFixedSize(true)
        holder.tvTitle.text = dataItems[position].id.toString()
    }

    override fun getItemCount(): Int {
        return dataItems.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var recyclerView: RecyclerView = itemView.findViewById<View>(R.id.rvPhotos) as RecyclerView
        var tvTitle: TextView = itemView.findViewById<View>(R.id.tvAlbumTitleText) as TextView

    }
}