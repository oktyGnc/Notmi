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
import com.oktaygenc.notmi.databinding.FragmentAddNoteBinding
import com.oktaygenc.notmi.presentation.ToolbarTitleListener
import com.oktaygenc.notmi.presentation.viewmodel.NoteViewModel
import com.oktaygenc.notmi.utils.ToolbarTitle
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddNoteFragment : Fragment() {
    private var toolbarTitleListener: ToolbarTitleListener? = null
    private var _binding: FragmentAddNoteBinding? = null
    private val binding get() = _binding!!
    private val noteViewModel: NoteViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentAddNoteBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        toolbarTitleListener = context as? ToolbarTitleListener
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbarTitleListener?.setName(ToolbarTitle.NEW)

        binding.btnSave.setOnClickListener {
            val id = 0
            val title = binding.etTitle.text.toString()
            val content = binding.etContent.text.toString()
            if (title.isNotEmpty() && content.isNotEmpty()) {
                val newNote = NoteEntity(id = id, title = title, content = content)
                noteViewModel.addNote(newNote)
            }
            findNavController().navigate(R.id.action_addNoteFragment_to_noteListFragment)
        }
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
