package com.doanducdat.noteapp.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.doanducdat.noteapp.R
import com.doanducdat.noteapp.model.Note
import com.doanducdat.noteapp.viewmodel.NoteViewModel
import kotlinx.android.synthetic.main.activity_update_note.*
import kotlinx.android.synthetic.main.item_note.*
import kotlinx.android.synthetic.main.item_note.edtDescription

class UpdateNoteActivity : AppCompatActivity() {

    private val noteViewMode: NoteViewModel by lazy {
        ViewModelProvider(
            this,
            NoteViewModel.NoteViewModelFactory(this.application)
        ).get(NoteViewModel::class.java)
    }
    private lateinit var note:Note
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_note)

        note = intent.getSerializableExtra("UPDATE_NOTE") as Note
        edtTitle.setText(note.title.toString())
        edtDescriptionUpdate.setText(note.description.toString())
        imgOfNote.load(note.Bitmap)
        initEvents()
    }

    private fun initEvents() {
        btnUpdate.setOnClickListener {
            note.title = edtTitle.text.toString()
            note.description = edtDescriptionUpdate.text.toString()
            noteViewMode.updateNote(note)
            finish()
        }
    }
}