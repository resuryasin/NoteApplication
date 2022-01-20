package re.sin.noteapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import re.sin.noteapplication.databinding.ActivityMainBinding
import re.sin.noteapplication.view.NotesRVAdapter
import re.sin.noteapplication.entities.Notes
import re.sin.noteapplication.viewmodel.NotesViewModel

class MainActivity : AppCompatActivity(), NotesRVAdapter.NotesClickInterface, NotesRVAdapter.NotesClickDeleteInterface {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    private val notesViewModel: NotesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val noteRVAdapter = NotesRVAdapter(this, this, this)

        with(binding.notesRV){
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = noteRVAdapter
        }

        notesViewModel.allNotes.observe(this, Observer { list ->
            list?.let {
                noteRVAdapter.updateList(it)
            }
        })

        binding.idFAB.setOnClickListener {
            startActivity(Intent(this@MainActivity, AddEditNoteActivity::class.java))
            this.finish()
        }
    }

    override fun onNoteClick(notes: Notes) {
        val intent = Intent(this@MainActivity, AddEditNoteActivity::class.java)
        intent.putExtra("noteType", "Edit")
        intent.putExtra("noteTitle", notes.noteTitle)
        intent.putExtra("noteDescription", notes.noteDescription)
        intent.putExtra("noteId", notes.id)
        startActivity(intent)
        this.finish()
    }

    override fun onDeleteIconClick(notes: Notes) {
        notesViewModel.deleteNote(notes)
        Toast.makeText(this, "${notes.noteTitle} Deleted", Toast.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}