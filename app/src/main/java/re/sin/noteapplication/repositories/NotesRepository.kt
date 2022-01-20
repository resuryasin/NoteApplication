package re.sin.noteapplication.repositories

import androidx.lifecycle.LiveData
import re.sin.noteapplication.dao.NotesDao
import re.sin.noteapplication.entities.Notes

class NotesRepository(private val notesDao: NotesDao) {
    val allNotes: LiveData<List<Notes>> = notesDao.getAllNotes()

    suspend fun insert(notes: Notes) {
        notesDao.insert(notes)
    }

    suspend fun delete(notes: Notes){
        notesDao.delete(notes)
    }

    suspend fun update(notes: Notes){
        notesDao.update(notes)
    }
}