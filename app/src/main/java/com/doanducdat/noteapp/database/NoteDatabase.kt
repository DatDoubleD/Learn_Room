package com.doanducdat.noteapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.doanducdat.noteapp.Converters
import com.doanducdat.noteapp.database.dao.NoteDao
import com.doanducdat.noteapp.model.Note

// danh sach class Entity da tao trong model de @Database tao ra bang cho ta
@Database(entities = [Note::class], version = 1) //version = 0 -> error
@TypeConverters(Converters::class) // có "s"
abstract class NoteDatabase: RoomDatabase() {
    abstract fun getNoteDao():NoteDao

    companion object{
        @Volatile // bien nay nam trong bo nho, chi tao 1 lan
        private var instance: NoteDatabase? = null

        fun getInstance(context: Context):NoteDatabase{
            if (instance == null){
                instance = Room.databaseBuilder(context, NoteDatabase::class.java, "NoteDatabase").build()
            }
            return instance!! // chắc chắn k null
        }
    }
}