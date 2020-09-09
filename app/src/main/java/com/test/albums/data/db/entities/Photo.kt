package com.test.albums.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import java.io.Serializable

@Entity(tableName = "photo")
data class Photo(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    @field:Json(name = "id")
    var id: Int,

    @ColumnInfo(name = "albumId")
    @field:Json(name = "albumId")
    var albumId: Int,

    @ColumnInfo(name = "title")
    @field:Json(name = "title")
    var title: String = "",

    @ColumnInfo(name = "url")
    @field:Json(name = "url")
    var url: String = "",

    @ColumnInfo(name = "thumbnailUrl")
    @field:Json(name = "thumbnailUrl")
    var thumbnailUrl: String = ""
) : Serializable