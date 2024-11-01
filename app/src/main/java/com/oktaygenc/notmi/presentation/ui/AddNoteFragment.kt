package com.oktaygenc.notmi.presentation.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
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
        val view = binding.root
        return view
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        toolbarTitleListener = context as? ToolbarTitleListener
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbarTitleListener?.setName(ToolbarTitle.NEW)
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