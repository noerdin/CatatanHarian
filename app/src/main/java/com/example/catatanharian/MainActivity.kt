package com.example.catatanharian

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.Toast
import com.example.catatanharian.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var noteAdapter: NoteAdapter
    private var notes = mutableListOf<Note>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        noteAdapter = NoteAdapter(this, notes)
        binding.noteListView.adapter = noteAdapter

        binding.saveButton.setOnClickListener {
            val title = binding.titleEditText.text.toString()
            val content = binding.contentEditText.text.toString()

            if (title.isNotBlank() && content.isNotBlank()) {
                val newNote = Note(title = title, content = content)
                notes.add(newNote)
                noteAdapter.notifyDataSetChanged()
                clearInputFields()
            } else {
                Toast.makeText(this, "Judul dan isi catatan tidak boleh kosong!", Toast.LENGTH_SHORT).show()
            }
        }

        binding.noteListView.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            val selectedNote = notes[position]
            showNoteDetail(selectedNote)
        }
    }

    private fun clearInputFields() {
        binding.titleEditText.text.clear()
        binding.contentEditText.text.clear()
    }

    private fun showNoteDetail(note: Note) {
        val detailDialog = FragmentNoteDetailDialog.newInstance(note)
        detailDialog.show(supportFragmentManager, "NoteDetailDialog")
    }

}