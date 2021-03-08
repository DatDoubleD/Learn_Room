package com.doanducdat.noteapp.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.doanducdat.noteapp.database.repository.NoteRepository
import com.doanducdat.noteapp.model.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(app: Application):ViewModel() {
    private var noteRepository:NoteRepository = NoteRepository(app)

    fun insertNote(note:Note) = viewModelScope.launch(Dispatchers.IO) {
        noteRepository.insertNote(note)
    }

    fun updateNote(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        noteRepository.updateNote(note)
    }

    fun deleteNote(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        noteRepository.deleteNote(note)
    }

    fun searchNote(searchQuery: String): LiveData<List<Note>>{
        return noteRepository.searchNote(searchQuery).asLiveData()
    }

    //lay data tu databse vi vay khi deletenote k co livedata nhưng nó vẫn tác động database, nen UI van dc update
    fun getAllNote():LiveData<List<Note>> = noteRepository.getAllNote()

    //con class nay chua hieu(có cách tạo khác trong file word room - xem lại)
    class NoteViewModelFactory(private val app: Application): ViewModelProvider.Factory{

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(NoteViewModel::class.java)){
                return NoteViewModel(app) as T
            }
            throw IllegalArgumentException("Unable construct viewModel")
        }
    }
}