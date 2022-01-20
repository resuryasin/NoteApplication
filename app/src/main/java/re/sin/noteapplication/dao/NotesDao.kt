package re.sin.noteapplication.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import re.sin.noteapplication.entities.Notes

@Dao
interface NotesDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(notes : Notes)

    @Delete
    suspend fun delete(notes: Notes)

    @Update
    suspend fun update(notes: Notes)

    @Query("Select * from notesTable order by id ASC")
    fun getAllNotes(): LiveData<List<Notes>>
}