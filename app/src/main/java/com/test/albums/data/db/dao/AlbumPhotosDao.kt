package com.test.albums.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.test.albums.data.db.entities.Album
import com.test.albums.data.db.entities.Photo
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface AlbumPhotosDao {

    @Query("SELECT * FROM photo WHERE albumId = :albumId")
    fun gePhotosByAlbumId(albumId: Int): Flowable<List<Photo>>

    @Query("SELECT * FROM photo")
    fun getAllPhotos(): Flowable<List<Photo>>

    @Query("SELECT * FROM album")
    fun getAllAlbums(): Flowable<List<Album>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAlbums(albums: List<Album>): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPhotos(photos: List<Photo>): Completable
}