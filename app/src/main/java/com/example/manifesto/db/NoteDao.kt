package com.example.manifesto.db

import android.provider.UserDictionary
import androidx.room.*

@Dao
interface NoteDao {

    @Insert
    suspend fun addnote(note: Note)

    @Insert
    suspend fun addMultipleNotes(vararg note: Note)

    @Query("SELECT * FROM note ORDER BY id DESC")
    suspend fun getAllNotes():List<Note>

    @Update
    suspend fun updateNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)
}