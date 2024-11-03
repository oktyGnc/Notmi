package com.oktaygenc.notmi.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oktaygenc.notmi.data.model.NoteEntity
import com.oktaygenc.notmi.data.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(private val repository: NoteRepository) : ViewModel() {

    private val _notes = MutableLiveData<List<NoteEntity>>()
    val notes: LiveData<List<NoteEntity>> get() = _notes
    private var allNotes: List<NoteEntity> = listOf()

    init {
        loadAllNotes()
    }

    private fun loadAllNotes() {
        viewModelScope.launch {
            allNotes = repository.getAllNotes()
            _notes.value = allNotes
        }
    }

    fun deleteNoteById(id: Int) {
        viewModelScope.launch {
            try {
                withContext(Dispatchers.IO) {
                    repository.deleteNoteById(id)
                }
                loadAllNotes()
            } catch (e: Exception) {
                Log.e("DeleteNoteById", "Error deleting note", e)
            }
        }
    }

    fun addNote(note: NoteEntity) {
        viewModelScope.launch {
            repository.insertNote(note)
            loadAllNotes()
        }
    }

    fun updateNote(note: NoteEntity) {
        viewModelScope.launch {
            repository.updateNote(note)
            loadAllNotes()
        }
    }

    fun searchNotes(query: String) {
        _notes.value = if (query.isEmpty()) {
            allNotes
        } else {
            allNotes.filter { note ->
                note.title?.contains(query, ignoreCase = true) == true || note.content?.contains(
                    query, ignoreCase = true
                ) == true
            }
        }
    }
}
