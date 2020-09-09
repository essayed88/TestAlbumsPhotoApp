package com.test.albums.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import java.io.Serializable

@Entity(tableName = "album")
data class Album(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    @field:Json(name = "id")
    var id: Int = 0,

    @ColumnInfo(name = "userId")
    @field:Json(name = "userId")
    var name: String = "",

    @ColumnInfo(name = "title")
    @field:Json(name = "title")
    var title: String = "",
    @Ignore
    var photosList: MutableList<Photo> = arrayListOf()

) : Serializable