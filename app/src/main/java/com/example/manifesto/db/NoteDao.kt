package com.example.manifesto.db

import android.provider.UserDictionary
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface NoteDao {

    @Insert
    fun addnote(note: Note)

    @Insert
    fun addMultipleNotes(vararg note: Note)

    @Query("SELECT * FROM note")
    fun getAllNotes():List<Note>
}