package com.doanducdat.noteapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.doanducdat.noteapp.R
import com.doanducdat.noteapp.model.Note

class NoteAdapter(
    private val context: Context,
    private val onClick: (Note) ->Unit,
    private val onDelete: (Note) -> Unit
) : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    private var notes: List<Note> = listOf()

    fun setNotes(notes: List<Note>) {
        this.notes = notes
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.item_note, parent, false)
        return NoteViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.onBind(notes[position])
    }

    override fun getItemCount(): Int = notes.size

    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val txtTitle: TextView = itemView.findViewById(R.id.txtTitle)
        private val edtDescription: EditText = itemView.findViewById(R.id.edtDescription)
        private val btnDelete: ImageView = itemView.findViewById(R.id.imgDelete)
        private val layoutNoteItem: LinearLayout = itemView.findViewById(R.id.layout_note_item)
        private val ImageShow: ImageView = itemView.findViewById(R.id.imgShow)
        fun onBind(note: Note) {
            txtTitle.text = note.title
            edtDescription.setText(note.description)
            ImageShow.setImageBitmap(note.Bitmap)
            btnDelete.setOnClickListener { onDelete(note) }
            layoutNoteItem.setOnClickListener{onClick(note)}
        }
    }
}