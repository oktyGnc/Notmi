package com.oktaygenc.notmi.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NoteEntity (
    @PrimaryKey val id : Int,
    @ColumnInfo(name = "title") val title : String?,
    @ColumnInfo(name = "content") val content : String?
)