package com.test.albums.data.remote.api

import com.test.albums.core.Endpoints
import com.test.albums.data.db.entities.Album
import com.test.albums.data.db.entities.Photo
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(Endpoints.GET_ALBUMS)
    fun getAlbumsList(): Single<List<Album>>

    @GET(Endpoints.GET_PHOTOS)
    fun getPhotosList(): Single<List<Photo>>
}