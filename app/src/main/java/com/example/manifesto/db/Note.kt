package com.example.manifesto.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
//Every entity represents a table in the database
@Entity
data class Note(
    val title:String,
    val note:String
):Serializable{
@PrimaryKey(autoGenerate = true)
    var id :Int=0
}