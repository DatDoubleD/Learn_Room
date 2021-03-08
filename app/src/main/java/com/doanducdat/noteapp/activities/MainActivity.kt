package com.doanducdat.noteapp.activities

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.Menu

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.doanducdat.noteapp.R


import com.doanducdat.noteapp.adapter.NoteAdapter
import com.doanducdat.noteapp.model.Note
import com.doanducdat.noteapp.viewmodel.NoteViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    private val noteViewMode: NoteViewModel by lazy {
        ViewModelProvider(
            this,
            NoteViewModel.NoteViewModelFactory(this.application)
        ).get(NoteViewModel::class.java)
    }
    private lateinit var adapter: NoteAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initControls()
        initEvents()
    }

    private fun initEvents() {
        btnOpenAddNoteActivity.setOnClickListener {
            val intent: Intent = Intent(this, AddNoteActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initControls() {
        adapter = NoteAdapter(this@MainActivity, onItemClick, onItemDelete)
        rcvNotes.setHasFixedSize(true)
        rcvNotes.layoutManager = LinearLayoutManager(this)
        rcvNotes.adapter = adapter
        //lay data tu livedata len adapter(adapter da gắn vào recycler rùi)
        getAllNote()
    }

    private val onItemClick: (Note) -> Unit = {
        val intent: Intent = Intent(this, UpdateNoteActivity::class.java)
        intent.putExtra("UPDATE_NOTE", it)
        //bitmap phải chuyển bytearray mới pass dc, nên lưu bitmap trong class note là kiểu bytearray
        startActivity(intent)
    }
    private val onItemDelete: (Note) -> Unit = {
        noteViewMode.deleteNote(it)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        val search = menu?.findItem(R.id.mnu_search)
        val searchView: SearchView = search?.actionView as SearchView
        searchView.isSubmitButtonEnabled = true
        searchView.setOnQueryTextListener(this)

        return true
    }

    override fun onQueryTextSubmit(query: String): Boolean {
        if (!TextUtils.isEmpty(query)) {
            searchNote(query)
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if (TextUtils.isEmpty(newText)) {
            getAllNote()
        }
        return true
    }

    private fun searchNote(query: String) {
        val searchQuery: String = "%$query%"
        noteViewMode.searchNote(searchQuery).observe(this, {
            if (it != null) {
                adapter.setNotes(it)
            }
        })
    }

    private fun getAllNote() {
        noteViewMode.getAllNote().observe(this, Observer {
            adapter.setNotes(it)
        })
    }


}