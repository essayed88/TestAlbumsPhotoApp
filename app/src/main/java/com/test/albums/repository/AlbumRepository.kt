package com.test.albums.repository

import com.test.albums.data.db.entities.Album
import com.test.albums.data.db.entities.Photo
import io.reactivex.Flowable
import io.reactivex.Single

interface AlbumRepository {

    fun getAlbums(): Single<List<Album>>

    fun getAlbumsDB(): Flowable<List<Album>>

    fun getAlbumPhotos(): Single<List<Photo>>

    fun getAlbumPhotoDB(): Flowable<List<Photo>>

    fun insertAlbums(albums: List<Album>)

    fun insertPhotos(photos: List<Photo>)

}