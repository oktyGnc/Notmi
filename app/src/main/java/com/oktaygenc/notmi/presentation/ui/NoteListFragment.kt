package com.oktaygenc.notmi.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.oktaygenc.notmi.data.model.NoteEntity
import com.oktaygenc.notmi.databinding.FragmentNoteListBinding
import com.oktaygenc.notmi.presentation.adapter.NoteAdapter
import com.oktaygenc.notmi.presentation.viewmodel.NoteViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NoteListFragment : Fragment() {

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        val notes = listOf(
            NoteEntity(id = 1, title = "Grocery List", content = "Eggs, Milk, Bread, Butter"),
            NoteEntity(
                id = 2,
                title = "Meeting Notes",
                content = "Discuss project timeline and milestones."
            ),
            NoteEntity(
                id = 3,
                title = "Book Recommendations",
                content = "1. The Great Gatsby\n2. To Kill a Mockingbird\n3. 1984"
            ),
            NoteEntity(
                id = 4,
                title = "Travel Plans",
                content = "Visit Paris in June. Book flights and hotel."
            ),
            NoteEntity(id = 5, title = "Grocery List", content = "Eggs, Milk, Bread, Butter"),
            NoteEntity(
                id = 6,
                title = "Meeting Notes",
                content = "Discuss project timeline and milestones."
            ),
            NoteEntity(
                id = 7,
                title = "Book Recommendations",
                content = "1. The Great Gatsby\n2. To Kill a Mockingbird\n3. 1984"
            ),
            NoteEntity(
                id = 8,
                title = "Travel Plans",
                content = "Visit Paris in June. Book flights and hotel."
            )
        )

        val notesAdapter = NoteAdapter(notes)

        binding.recyclerView.apply {
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            adapter = notesAdapter
        }


    }

//        binding.recyclerViewNotes.apply {
//            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
//            adapter = notesAdapter
//        }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}