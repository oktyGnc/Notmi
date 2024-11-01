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

    init {
        getAllNotes()
    }

    private fun getAllNotes() {
        viewModelScope.launch {
            _notes.value = repository.getAllNotes()
        }
    }

    fun addNote(note: NoteEntity) {
        viewModelScope.launch {
            repository.insertNote(note)
            _notes.value = repository.getAllNotes().reversed()
        }
    }

    fun deleteNoteById(id: Int) {
        viewModelScope.launch {
            repository.deleteNoteById(id)
            getAllNotes()
        }
    }


    fun updateNote(note: NoteEntity) {
        viewModelScope.launch {
            repository.updateNote(note)
            getAllNotes()
        }
    }

    fun searchNotes(query: String) {
        viewModelScope.launch {
            val allNotes = repository.getAllNotes()
            _notes.value = allNotes.filter { note ->
                note.title?.contains(query, ignoreCase = true) == true || note.content?.contains(
                    query,
                    ignoreCase = true
                ) == true
            }
        }
    }

}