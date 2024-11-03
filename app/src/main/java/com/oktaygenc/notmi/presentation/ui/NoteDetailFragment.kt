package com.oktaygenc.notmi.presentation.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.oktaygenc.notmi.R
import com.oktaygenc.notmi.data.model.NoteEntity
import com.oktaygenc.notmi.databinding.FragmentNoteDetailBinding
import com.oktaygenc.notmi.presentation.ToolbarTitleListener
import com.oktaygenc.notmi.presentation.viewmodel.NoteViewModel
import com.oktaygenc.notmi.utils.ToolbarTitle
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NoteDetailFragment : Fragment() {

    private var toolbarTitleListener: ToolbarTitleListener? = null
    private var _binding: FragmentNoteDetailBinding? = null
    private val binding get() = _binding!!
    private val noteViewModel: NoteViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentNoteDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        toolbarTitleListener = context as? ToolbarTitleListener
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbarTitleListener?.setName(ToolbarTitle.UPDATE)

        val noteId = arguments?.getInt("noteId") ?: 0
        val noteTitle = arguments?.getString("noteTitle") ?: ""
        val noteContent = arguments?.getString("noteContent") ?: ""

        initializeUI(noteTitle, noteContent)
        with(binding) {
            btnCheck.setOnClickListener {
                updateNote(noteId)
            }
            btnDelete.setOnClickListener {
                deleteNoteById(noteId)
            }
        }
    }

    private fun initializeUI(noteTitle: String, noteContent: String) {
        binding.etTitle.setText(noteTitle)
        binding.etContent.setText(noteContent)
    }

    private fun updateNote(noteId: Int) {
        val updatedNote = NoteEntity(
            id = noteId,
            title = binding.etTitle.text.toString(),
            content = binding.etContent.text.toString()
        )
        noteViewModel.updateNote(updatedNote)
        navigateToNoteList()
    }

    private fun deleteNoteById(noteId: Int) {
        noteViewModel.deleteNoteById(noteId)
        navigateToNoteList()
    }

    private fun navigateToNoteList() {
        findNavController().navigate(R.id.action_noteDetailFragment_to_noteListFragment)
    }

    override fun onDetach() {
        super.onDetach()
        toolbarTitleListener = null
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
