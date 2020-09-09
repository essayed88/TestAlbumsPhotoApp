package com.test.albums.repository

import android.annotation.SuppressLint
import android.util.Log
import com.test.albums.data.db.dao.AlbumPhotosDao
import com.test.albums.data.db.entities.Album
import com.test.albums.data.db.entities.Photo
import com.test.albums.data.remote.api.ApiService
import com.test.albums.view.activities.AlbumsListActivity.Companion.TAG
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class AlbumRepositoryImp(
    private val serviceAPI: ApiService,
    private val albumsPhotosDao: AlbumPhotosDao
) : AlbumRepository {

    override fun getAlbums(): Single<List<Album>> {
        return serviceAPI.getAlbumsList()
            .flatMap {
                insertAlbums(it)
                Single.just(it)
            }
    }

    override fun getAlbumsDB(): Flowable<List<Album>> = albumsPhotosDao.getAllAlbums()

    override fun getAlbumPhotos(): Single<List<Photo>> {
        return serviceAPI.getPhotosList()
            .flatMap {
                insertPhotos(it)
                Single.just(it)
            }
    }

    override fun getAlbumPhotoDB(): Flowable<List<Photo>> = albumsPhotosDao.getAllPhotos()

    @SuppressLint("CheckResult")
    override fun insertAlbums(albums: List<Album>) {
        albumsPhotosDao.insertAlbums(albums)
            .subscribeOn(Schedulers.io())
            .subscribeBy(
                onComplete = {
                    Log.i(TAG, "onComplete")
                },
                onError = {
                    Log.i(TAG, it.message ?: "error")
                }
            )
    }

    @SuppressLint("CheckResult")
    override fun insertPhotos(photos: List<Photo>) {
        albumsPhotosDao.insertPhotos(photos)
            .subscribeOn(Schedulers.io())
            .subscribeBy(
                onComplete = {
                    Log.i(TAG, "onComplete")
                },
                onError = {
                    Log.i(TAG, it.message ?: "error")
                }
            )
    }

}