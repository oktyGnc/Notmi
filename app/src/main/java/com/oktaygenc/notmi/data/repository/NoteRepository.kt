package com.oktaygenc.notmi.data.repository

import com.oktaygenc.notmi.data.model.NoteEntity

interface NoteRepository {
    suspend fun insertNote(note: NoteEntity)
    suspend fun deleteNoteById(id: Int)
    suspend fun getAllNotes(): List<NoteEntity>
    suspend fun deleteNote(note: NoteEntity)
    suspend fun updateNote(note: NoteEntity)
    suspend fun getNoteById(id: Int): NoteEntity?
}
