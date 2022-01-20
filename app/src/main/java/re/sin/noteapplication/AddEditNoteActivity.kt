package re.sin.noteapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import re.sin.noteapplication.databinding.ActivityAddEditNoteBinding
import re.sin.noteapplication.entities.Notes
import re.sin.noteapplication.viewmodel.NotesViewModel
import java.text.SimpleDateFormat
import java.util.*

class AddEditNoteActivity : AppCompatActivity() {
    private var _binding: ActivityAddEditNoteBinding? = null
    private val binding get() = _binding!!
    private val notesViewModel: NotesViewModel by viewModels()

    var noteID = -1;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityAddEditNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val noteType = intent.getStringExtra("noteType")
        if (noteType.equals("Edit")) {
            noteID = intent.getIntExtra("noteId", -1)
            with(binding){
                idBtn.text = "Update Note"
                idEdtNoteName.setText(intent.getStringExtra("noteTitle"))
                idEdtNoteDesc.setText(intent.getStringExtra("noteDescription"))
            }
        } else {
            binding.idBtn.text = "Save Note"
        }
        binding.idBtn.setOnClickListener {
            val noteTitle = binding.idEdtNoteName.text.toString()
            val noteDescription = binding.idEdtNoteDesc.text.toString()
            if (noteType.equals("Edit")) {
                if (noteTitle.isNotEmpty() && noteDescription.isNotEmpty()) {
                    val sdf = SimpleDateFormat("dd MMM, yyyy - HH:mm")
                    val currentDateAndTime: String = sdf.format(Date())
                    val updatedNotes = Notes(noteTitle, noteDescription, currentDateAndTime)
                    updatedNotes.id = noteID
                    notesViewModel.updateNote(updatedNotes)
                    Toast.makeText(this, "Note Updated..", Toast.LENGTH_LONG).show()
                }
            } else {
                if (noteTitle.isNotEmpty() && noteDescription.isNotEmpty()) {
                    val sdf = SimpleDateFormat("dd MMM, yyyy - HH:mm")
                    val currentDateAndTime: String = sdf.format(Date())
                    notesViewModel.addNote(Notes(noteTitle, noteDescription, currentDateAndTime))
                    Toast.makeText(this, "$noteTitle Added", Toast.LENGTH_LONG).show()
                }
            }
            startActivity(Intent(applicationContext, MainActivity::class.java))
            this.finish()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}