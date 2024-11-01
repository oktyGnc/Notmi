package com.oktaygenc.notmi.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.oktaygenc.notmi.data.model.NoteEntity
import com.oktaygenc.notmi.databinding.ItemViewNoteBinding
import com.oktaygenc.notmi.presentation.ui.NoteListFragmentDirections

class NoteAdapter (private var notes: List<NoteEntity>) : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    inner class NoteViewHolder(private val binding: ItemViewNoteBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(note:NoteEntity){
            binding.textViewTitle.text = note.title
            binding.textViewContent.text = note.content
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val binding = ItemViewNoteBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return NoteViewHolder(binding)
    }

    override fun getItemCount(): Int = notes.size


    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(notes[position])
        holder.itemView.setOnClickListener {
            val action = NoteListFragmentDirections.actionNoteListFragmentToNoteDetailFragment()
            val navController = Navigation.findNavController(holder.itemView)
            navController.navigate(action)
        }
    }

    fun updateNotes(newNotes: List<NoteEntity>) {
        notes = newNotes
        notifyDataSetChanged()
    }
}