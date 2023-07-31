package com.example.catatanharian

import android.os.Bundle
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.fragment_note_detail_dialog.closeButton
import kotlinx.android.synthetic.main.fragment_note_detail_dialog.contentTextView
import kotlinx.android.synthetic.main.fragment_note_detail_dialog.titleTextView

class FragmentNoteDetailDialog : DialogFragment() {

    companion object {
        private const val ARG_NOTE = "arg_note"

        fun newInstance(note: Note): FragmentNoteDetailDialog {
            val args = Bundle()
            args.putParcelable(ARG_NOTE, note)
            val fragment = FragmentNoteDetailDialog()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL, R.style.AppTheme_FullScreenDialog)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_note_detail_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val note = arguments?.getParcelable<Note>(ARG_NOTE)

        note?.let {
            titleTextView.text = it.title
            contentTextView.text = it.content
        }

        closeButton.setOnClickListener {
            dismiss()
        }
    }
}
