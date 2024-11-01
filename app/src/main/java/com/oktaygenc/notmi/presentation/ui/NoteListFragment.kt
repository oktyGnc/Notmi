package com.oktaygenc.notmi.presentation.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.oktaygenc.notmi.R
import com.oktaygenc.notmi.data.model.NoteEntity
import com.oktaygenc.notmi.databinding.FragmentNoteListBinding
import com.oktaygenc.notmi.presentation.ToolbarTitleListener
import com.oktaygenc.notmi.presentation.adapter.NoteAdapter
import com.oktaygenc.notmi.presentation.viewmodel.NoteViewModel
import com.oktaygenc.notmi.utils.ToolbarTitle
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NoteListFragment : Fragment() {
    private var toolbarTitleListener: ToolbarTitleListener? = null
    private var _binding: FragmentNoteListBinding? = null
    private val binding get() = _binding!!
    private val noteViewModel: NoteViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentNoteListBinding.inflate(layoutInflater, container, false)
        val view = binding.root
        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        toolbarTitleListener = context as? ToolbarTitleListener
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        with(binding) {
            icAdd.setOnClickListener {
                findNavController().navigate(R.id.action_noteListFragment_to_addNoteFragment)
            }

        }
        toolbarTitleListener?.setName(ToolbarTitle.LIST)
    }

    override fun onDetach() {
        super.onDetach()
        toolbarTitleListener = null
    }

    private fun setupRecyclerView() {
       val notesAdapter = NoteAdapter(emptyList())
        binding.recyclerView.apply {
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            adapter = notesAdapter
        }

        noteViewModel.notes.observe(viewLifecycleOwner) { notes ->
            notesAdapter.updateNotes(notes.reversed())
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}