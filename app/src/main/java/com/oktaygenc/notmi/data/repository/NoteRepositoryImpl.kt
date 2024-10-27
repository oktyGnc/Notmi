package com.oktaygenc.notmi.data.repository

import com.oktaygenc.notmi.data.local.NoteDao
import com.oktaygenc.notmi.data.model.NoteEntity
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(private val noteDao: NoteDao) : NoteRepository {

    override suspend fun insertNote(note: NoteEntity) {
        noteDao.insert(note)
    }

    override suspend fun deleteNoteById(id: Int) {
        noteDao.getNoteById(id)?.let { note ->
            noteDao.delete(note)
        }
    }

    override suspend fun getAllNotes(): List<NoteEntity> {
        return noteDao.getAllNotes()
    }

    override suspend fun deleteNote(note: NoteEntity) {
        noteDao.delete(note)
    }

    override suspend fun updateNote(note: NoteEntity) {
        noteDao.update(note)
    }

    override suspend fun getNoteById(id: Int): NoteEntity? {
        return noteDao.getNoteById(id)
    }
}
