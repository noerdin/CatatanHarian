package com.example.catatanharian

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.contentEditText
import kotlinx.android.synthetic.main.activity_main.noteListView
import kotlinx.android.synthetic.main.activity_main.saveButton
import kotlinx.android.synthetic.main.activity_main.titleEditText

class MainActivity : AppCompatActivity() {

    private lateinit var noteAdapter: NoteAdapter
    private var notes = mutableListOf<Note>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        noteAdapter = NoteAdapter(this, notes)
        noteListView.adapter = noteAdapter

        saveButton.setOnClickListener {
            val title = titleEditText.text.toString()
            val content = contentEditText.text.toString()

            if (title.isNotBlank() && content.isNotBlank()) {
                val newNote = Note(title = title, content = content)
                notes.add(newNote)
                noteAdapter.notifyDataSetChanged()
                clearInputFields()
            } else {
                Toast.makeText(this, "Judul dan isi catatan tidak boleh kosong!", Toast.LENGTH_SHORT).show()
            }
        }

        noteListView.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            val selectedNote = notes[position]
            showNoteDetail(selectedNote)
        }
    }

    private fun clearInputFields() {
        titleEditText.text.clear()
        contentEditText.text.clear()
    }

    private fun showNoteDetail(note: Note) {
        val detailDialog = FragmentNoteDetailDialog.newInstance(note)
        detailDialog.show(supportFragmentManager, "NoteDetailDialog")
    }

}