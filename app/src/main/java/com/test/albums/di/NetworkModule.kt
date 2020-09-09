package com.test.albums.di

import com.test.albums.core.Network
import com.test.albums.data.remote.api.ApiService
import org.koin.dsl.module

val networkModule = module {
    single { Network().createWebService().create(ApiService::class.java) }
}