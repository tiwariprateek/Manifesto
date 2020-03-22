package com.example.manifesto.ui

import android.os.AsyncTask
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.example.manifesto.R
import com.example.manifesto.db.Note
import com.example.manifesto.db.NoteDatabase
import kotlinx.android.synthetic.main.fragment_add_note.*

/**
 * A simple [Fragment] subclass.
 */
class AddNoteFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_note, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        savebttn.setOnClickListener {

            val noteTitle=edit_text_title.text.toString().trim()
            val noteBody=edit_text_note.text.toString().trim()

            if(noteTitle.isEmpty()){
                edit_text_title.error="Title Required"
                edit_text_title.requestFocus()
                return@setOnClickListener
            }

            if(noteBody.isEmpty()){
                edit_text_note.error="Note Required"
                edit_text_note.requestFocus()
                return@setOnClickListener
            }

            val note=Note(noteTitle,noteBody)

        }

    }



}
