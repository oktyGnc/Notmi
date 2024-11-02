package com.oktaygenc.notmi.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oktaygenc.notmi.data.model.NoteEntity
import com.oktaygenc.notmi.data.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
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

    fun addNote(note: NoteEntity) {
        viewModelScope.launch {
            repository.insertNote(note)
            loadAllNotes()
        }
    }

    fun deleteNoteById(id: Int) {
        viewModelScope.launch {
            repository.deleteNoteById(id)
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
                    query,
                    ignoreCase = true
                ) == true
            }
        }
    }
}
