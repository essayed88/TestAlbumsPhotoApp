package com.test.albums.di

import androidx.room.Room
import com.test.albums.data.db.DataBase
import org.koin.dsl.module

val roomModule = module {
    single { Room.databaseBuilder(get(), DataBase::class.java, "database_base").build() }
    single { get<DataBase>().albumPhotosDao() }
}