package com.doanducdat.noteapp.model

import android.graphics.Bitmap
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

//Room se tao ra bang cho ta tu class nay

@Entity(tableName = "note_table") // ten bang trong database
class Note(
    // @ColumInfo: ten cot, neu k dung se mac dinh lay ten thuoc tinh lam ten cot
    @ColumnInfo(name = "title_col") var title: String = "",
    @ColumnInfo(name = "description_col") var description: String = "",
    var Bitmap:Bitmap
) :Serializable{
    //khai bao thuoc tinh var not val, val chi co geter - k co setter k dung trong room
    @PrimaryKey(autoGenerate = true) // xac dinh khoa chinh + tu dong khoi tao khi k khai bao gi
    @ColumnInfo(name = "note_id_col")
    var id: Int = 0
}