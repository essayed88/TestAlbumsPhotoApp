package com.test.albums.view.activities

import android.os.Bundle
import android.util.Log
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.test.albums.R
import com.test.albums.data.db.entities.Album
import com.test.albums.view.adapters.AlbumsAdapter
import com.test.albums.viewmodels.AlbumsListViewModel
import com.test.albums.viewmodels.UIState
import kotlinx.android.synthetic.main.albums_list_layout.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class AlbumsListActivity : AppCompatActivity() {

    private val albumsLisTViewModel: AlbumsListViewModel by viewModel()

    private lateinit var albumsAdapter: AlbumsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.albums_list_layout)
        setupUI()
        setupHandler()
        albumsLisTViewModel.getAlbumsListDB()
    }

    private fun setupUI() {
        albumsAdapter = AlbumsAdapter()
        recyclerViewAlbums.apply {
            layoutManager =
                LinearLayoutManager(this@AlbumsListActivity, RecyclerView.VERTICAL, false)
            adapter = albumsAdapter
        }
    }

    private fun setupHandler() {
        albumsLisTViewModel.getAlbumsListDBLiveData().observe(this, Observer { status ->
            when (status) {
                is UIState.Loading -> {
                    Log.i(TAG, getString(R.string.loading_text))
                }
                is UIState.Success<*> -> {
                    val data = status.data as MutableList<Album>
                    if (data.count() != 0) {
                        albumsAdapter.setData(this, data)
                    } else {
                        albumsLisTViewModel.getAlbumsListAPI()
                    }
                }
                is UIState.Error -> {
                    empty_view.visibility = VISIBLE
                    Log.i(TAG, status.message)
                }
            }
        })

        albumsLisTViewModel.getAlbumsListAPILiveData().observe(this, Observer { status ->
            when (status) {
                is UIState.Loading -> {
                    progressBar.visibility = VISIBLE
                    Log.i(TAG, getString(R.string.loading_text))
                }
                is UIState.Success<*> -> {
                    progressBar.visibility = GONE
                    val data = status.data as MutableList<Album>
                    if (data.count() != 0) {
                        albumsAdapter.setData(this, data)
                    } else {
                        empty_view.visibility = VISIBLE
                    }
                }
                is UIState.Error -> {
                    empty_view.visibility = VISIBLE
                    Log.i(TAG, status.message)
                }
            }
        })
    }

    companion object {
        const val TAG = "AlbumsListActivity"
    }
}
