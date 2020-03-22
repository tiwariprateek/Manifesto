package com.example.manifesto.db

import android.provider.UserDictionary
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface NoteDao {

    @Insert
    suspend fun addnote(note: Note)

    @Insert
    suspend fun addMultipleNotes(vararg note: Note)

    @Query("SELECT * FROM note")
    suspend fun getAllNotes():List<Note>
}