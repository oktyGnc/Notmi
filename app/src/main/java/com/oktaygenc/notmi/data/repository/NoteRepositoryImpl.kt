package com.oktaygenc.notmi.data.repository

import com.oktaygenc.notmi.data.local.NoteDao
import com.oktaygenc.notmi.data.model.NoteEntity

class NoteRepositoryImpl(private val noteDao: NoteDao) {

    suspend fun addNote(note: NoteEntity) {
        noteDao.insert(note)
    }

    suspend fun getAllNotes(): List<NoteEntity> {
        return noteDao.getAllNotes()
    }

    suspend fun deleteNote(note: NoteEntity) {
        noteDao.delete(note)
    }

    suspend fun updateNote(note: NoteEntity) {
        noteDao.update(note)
    }

    suspend fun getNoteById(id: Int): NoteEntity? {
        return noteDao.getNoteById(id)
    }
}
