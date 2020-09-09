package com.test.albums.di

import com.test.albums.repository.AlbumRepository
import com.test.albums.repository.AlbumRepositoryImp
import org.koin.dsl.module

val applicationModule = module {
    factory<AlbumRepository> { AlbumRepositoryImp(get(), get()) }
}