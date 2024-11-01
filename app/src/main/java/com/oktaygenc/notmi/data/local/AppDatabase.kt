package com.oktaygenc.notmi.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import com.oktaygenc.notmi.data.model.NoteEntity

@Database(entities = [NoteEntity::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = androidx.room.Room.databaseBuilder(
                    context.applicationContext, AppDatabase::class.java, "note_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }


}