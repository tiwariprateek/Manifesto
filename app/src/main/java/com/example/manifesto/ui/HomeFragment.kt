package com.example.manifesto.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.StaggeredGridLayoutManager

import com.example.manifesto.R
import com.example.manifesto.db.NoteDatabase
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass.
Instead of using simple Fragment.
We will write our own BaseFragment class because
to execute the queries asyncly we will call Kotlin coroutines
and only write the coroutine scope once
 **/
class HomeFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }
//Most of the actions and Onclick listeners are performed under this method
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        recycler_view_notes.setHasFixedSize(true)
        recycler_view_notes.layoutManager=StaggeredGridLayoutManager(3,
            StaggeredGridLayoutManager.VERTICAL)

        launch {
            //let to check wheather the context is not null
            context?.let {
                val notes=NoteDatabase(it).getNoteDao().getAllNotes()
                recycler_view_notes.adapter=NotesAdapter(notes)
            }
        }
//HomeFragmentDirections is an auto generated class by the NavigationComponent
//Rebuild project to generate this class
        floatingactionbutton.setOnClickListener {
            val action=HomeFragmentDirections.actionAddNote()
            Navigation.findNavController(it).navigate(action)
        }
    }
}
