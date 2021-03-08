package com.doanducdat.noteapp.database.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.doanducdat.noteapp.database.NoteDatabase
import com.doanducdat.noteapp.database.dao.NoteDao
import com.doanducdat.noteapp.model.Note

class NoteRepository (app:Application){
    private val noteDao:NoteDao
    init {
        val noteDatabase: NoteDatabase = NoteDatabase.getInstance(app)
        noteDao = noteDatabase.getNoteDao()
    }

    suspend fun insertNote(note:Note) = noteDao.insertNote(note)
    suspend fun updateNote(note:Note) = noteDao.updateNote(note)
    suspend fun deleteNote(note:Note) = noteDao.deleteNote(note)
    fun searchNote(searchQuery: String) = noteDao.searchNote(searchQuery)

    fun getAllNote():LiveData<List<Note>> = noteDao.getAllNote()
}