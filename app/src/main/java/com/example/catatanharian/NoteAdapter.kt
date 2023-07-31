package com.example.catatanharian

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.fragment_note_detail_dialog.view.contentTextView
import kotlinx.android.synthetic.main.fragment_note_detail_dialog.view.titleTextView

class NoteAdapter(private val context: Context, private val notes: List<Note>) : BaseAdapter() {

    override fun getCount(): Int {
        return notes.size
    }

    override fun getItem(position: Int): Note {
        return notes[position]
    }

    override fun getItemId(position: Int): Long {
        return notes[position].id
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.fragment_note_detail_dialog, parent, false)

        val note = getItem(position)
        view.titleTextView.text = note.title
        view.contentTextView.text = note.content

        return view
    }
}