package com.oktaygenc.notmi.utils

import androidx.recyclerview.widget.DiffUtil
import com.oktaygenc.notmi.data.model.NoteEntity

class NoteDiffCallback(
    private val oldList: List<NoteEntity>,
    private val newList: List<NoteEntity>,
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}
