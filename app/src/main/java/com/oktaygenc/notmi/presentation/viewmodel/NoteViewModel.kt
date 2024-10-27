package com.oktaygenc.notmi.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.oktaygenc.notmi.data.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(private val repository: NoteRepository) : ViewModel()  {
}