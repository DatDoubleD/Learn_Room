package com.doanducdat.noteapp.activities

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import coil.ImageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult
import com.doanducdat.noteapp.R
import com.doanducdat.noteapp.model.Note
import com.doanducdat.noteapp.viewmodel.NoteViewModel
import kotlinx.android.synthetic.main.activity_add_note.*
import kotlinx.coroutines.*

class AddNoteActivity : AppCompatActivity() {
    private val noteViewMode: NoteViewModel by lazy {
        ViewModelProvider(
            this,
            NoteViewModel.NoteViewModelFactory(this.application)
        ).get(NoteViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)
        //event click add note
        btnAdd.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                var note = Note(edtTitle.text.toString(), edtDescription.text.toString(), getBitmap())
                noteViewMode.insertNote(note)
                finish()
            }
        }
    }

    //coil get bitmap from link
    private suspend fun getBitmap(): Bitmap {
        val loading: ImageLoader = ImageLoader(this)
        val request: ImageRequest = ImageRequest.Builder(this)
            .data("https://i.pinimg.com/originals/e6/e3/a9/e6e3a9d62d21465f79bb9ab86b2c5e04.jpg")
            .build()
        val result: Drawable = (loading.execute(request) as SuccessResult).drawable
        return (result as BitmapDrawable).bitmap
    }
}