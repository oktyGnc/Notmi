package com.oktaygenc.notmi.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import androidx.room.Delete
import com.oktaygenc.notmi.data.model.NoteEntity

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note: NoteEntity)

    @Update
    suspend fun update(note: NoteEntity)

    @Delete
    suspend fun delete(note: NoteEntity)

    @Query("SELECT * FROM NoteEntity WHERE id = :id")
    suspend fun getNoteById(id: Int): NoteEntity?

    @Query("SELECT * FROM NoteEntity")
    suspend fun getAllNotes(): List<NoteEntity>

    @Query("DELETE FROM NoteEntity WHERE id = :id")
    suspend fun deleteById(id: Int)
}
