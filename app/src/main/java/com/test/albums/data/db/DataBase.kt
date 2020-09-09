package com.test.albums.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.test.albums.data.db.dao.AlbumPhotosDao
import com.test.albums.data.db.entities.Album
import com.test.albums.data.db.entities.Photo

@Database(entities = [Album::class, Photo::class], version = 1)
abstract class DataBase : RoomDatabase() {
    abstract fun albumPhotosDao(): AlbumPhotosDao
}