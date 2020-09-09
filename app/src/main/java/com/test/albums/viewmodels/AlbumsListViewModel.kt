package com.test.albums.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test.albums.data.db.entities.Album
import com.test.albums.data.db.entities.Photo
import com.test.albums.repository.AlbumRepository
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.BiFunction
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class AlbumsListViewModel(private val albumRepository: AlbumRepository) : ViewModel() {

    private val albumsListDBMutableLiveData: MutableLiveData<UIState> = MutableLiveData()
    private val albumsListAPIMutableLiveData: MutableLiveData<UIState> = MutableLiveData()

    fun getAlbumsListDBLiveData(): LiveData<UIState> = albumsListDBMutableLiveData
    fun getAlbumsListAPILiveData(): LiveData<UIState> = albumsListAPIMutableLiveData

    private val subscriptions = CompositeDisposable()

    fun getAlbumsListDB() {
        subscriptions.add(
            Flowable.zip(
                albumRepository.getAlbumsDB(),
                albumRepository.getAlbumPhotoDB(),
                BiFunction<List<Album>, List<Photo>, List<Album>>
                { Albums, Photos -> addPhotosToAlbums(Albums, Photos) })
                .doOnSubscribe {
                    albumsListDBMutableLiveData.postValue(UIState.Loading)
                }.subscribeOn(Schedulers.io())
                .subscribeBy(
                    onNext = {
                        albumsListDBMutableLiveData.postValue(UIState.Success(it))
                    },
                    onError = {
                        albumsListDBMutableLiveData.postValue(
                            UIState.Error(
                                it.message
                                    ?: "Error"
                            )
                        )
                    }
                ))
    }

    private fun addPhotosToAlbums(albums: List<Album>, photos: List<Photo>): List<Album> {
        return albums.map { (id, userId, title) ->
            var firstPhotos = photos.asSequence()
                .filter { it.albumId == id }
                .sortedBy { it.id }
                .take(3)
                .toList()

            Album(id, userId, title, firstPhotos as MutableList<Photo>)
        }
    }

    fun getAlbumsListAPI() {
        subscriptions.add(
            Single.zip(albumRepository.getAlbums(),
                albumRepository.getAlbumPhotos(),
                BiFunction<List<Album>, List<Photo>, List<Album>>
                { Albums, Photos -> addPhotosToAlbums(Albums, Photos) })
                .doOnSubscribe {
                    albumsListAPIMutableLiveData.postValue(UIState.Loading)
                }.subscribeOn(Schedulers.io())
                .subscribeBy(
                    onSuccess = { data ->
                        albumsListAPIMutableLiveData.postValue(UIState.Success(data))
                    },
                    onError = {
                        albumsListAPIMutableLiveData.postValue(
                            UIState.Error(
                                it.message
                                    ?: "Error"
                            )
                        )
                    }
                ))
    }

    override fun onCleared() {
        super.onCleared()
        subscriptions.clear()
    }

}