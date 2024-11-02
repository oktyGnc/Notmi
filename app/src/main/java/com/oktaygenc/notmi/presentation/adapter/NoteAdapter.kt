package com.oktaygenc.notmi.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.oktaygenc.notmi.data.model.NoteEntity
import com.oktaygenc.notmi.databinding.ItemViewNoteBinding
import com.oktaygenc.notmi.utils.NoteDiffCallback

class NoteAdapter(
    private var notes: MutableList<NoteEntity> = mutableListOf(),
    private val onNoteClick: (NoteEntity) -> Unit,
) : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    inner class NoteViewHolder(private val binding: ItemViewNoteBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(note: NoteEntity) {
            binding.textViewTitle.text = note.title
            binding.textViewContent.text = note.content
            itemView.setOnClickListener {
                onNoteClick(note)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val binding =
            ItemViewNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteViewHolder(binding)
    }

    override fun getItemCount(): Int = notes.size


    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(notes[position])
    }

    fun updateNotes(newNotes: List<NoteEntity>) {
        val diffCallback = NoteDiffCallback(notes, newNotes)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        notes.clear()
        notes.addAll(newNotes)
        diffResult.dispatchUpdatesTo(this)
    }

}