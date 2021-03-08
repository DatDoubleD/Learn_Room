package com.doanducdat.noteapp.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.doanducdat.noteapp.model.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    //cac phuong thuc thao tac vs database se chay tren
    // mulptiThread vi vay khai bao la suspen funtion
    @Insert (onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertNote(note: Note)

    @Update
    suspend fun updateNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)

    @Query("select * from note_table")
    fun getAllNote():LiveData<List<Note>>

    //truong hop query co doi so
    @Query("select * from note_table where title_col=:title")
    fun getNoteByTitle(title:String):LiveData<List<Note>>

    @Query("select * from note_table where title_col like :searchQuery")
    fun searchNote(searchQuery:String): Flow<List<Note>>
}