package com.oktaygenc.notmi.di

import android.app.Application
import androidx.room.Room
import com.oktaygenc.notmi.data.local.AppDatabase
import com.oktaygenc.notmi.data.local.NoteDao
import com.oktaygenc.notmi.data.repository.NoteRepository
import com.oktaygenc.notmi.data.repository.NoteRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAppDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(
            application,
            AppDatabase::class.java,
            "note_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideNoteDao(appDatabase: AppDatabase): NoteDao {
        return appDatabase.noteDao()
    }

    @Provides
    @Singleton
    fun provideNoteRepository(noteRepositoryImpl: NoteRepositoryImpl): NoteRepository {
        return noteRepositoryImpl
    }
}




