package com.example.manifesto.ui

import android.app.AlertDialog
import android.os.AsyncTask
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.navigation.Navigation

import com.example.manifesto.R
import com.example.manifesto.db.Note
import com.example.manifesto.db.NoteDatabase
import kotlinx.android.synthetic.main.fragment_add_note.*
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass.
 */
class AddNoteFragment : BaseFragment() {
    private var note: Note? =null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_add_note, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        arguments?.let {
            note=AddNoteFragmentArgs.fromBundle(it).note
            edit_text_title.setText(note?.title)
            edit_text_note.setText(note?.note)
        }

        savebttn.setOnClickListener {view->

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
            launch {
                context?.let {
                    val mnote=Note(noteTitle,noteBody)
                    if (note==null){
                        NoteDatabase(it).getNoteDao().addnote(mnote)
                        it.toast("Note Saved")
                    }
                    else
                        mnote.id=note!!.id
                        NoteDatabase(it).getNoteDao().updateNote(mnote)
                        it.toast("Note Saved")
                    val action=AddNoteFragmentDirections.actionSaveNote()
                    Navigation.findNavController(view).navigate(action)
                }
            }

        }

    }

    private fun deletenote(){
        AlertDialog.Builder(context).apply {
            setTitle("Are you sure ?")
            setMessage("You cannot undo this operation")
            setPositiveButton("Yes"){_,_ ->
                launch {
                    NoteDatabase(context).getNoteDao().deleteNote(note!!)
                    val action=AddNoteFragmentDirections.actionSaveNote()
                    Navigation.findNavController(view!!).navigate(action)

                }
            }
            setNegativeButton("No"){_,_ ->}
        }.create()
            .show()
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.delete ->if (note!=null) deletenote() else context?.toast("Cannot Delete")
        }
        return super .onOptionsItemSelected(item)
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu,menu)
    }

}
