package re.sin.noteapplication.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import re.sin.noteapplication.R
import re.sin.noteapplication.entities.Notes

class NotesRVAdapter(
    val context: Context,
    private val noteClickDeleteInterface: NotesClickDeleteInterface,
    private val noteClickInterface: NotesClickInterface
) :
    RecyclerView.Adapter<NotesRVAdapter.ViewHolder>() {

    private val allNotes = ArrayList<Notes>()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val noteTV: TextView = itemView.findViewById(R.id.idTVNote)
        val dateTV: TextView = itemView.findViewById(R.id.idTVDate)
        val deleteIV: ImageView = itemView.findViewById(R.id.idIVDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.note_rv_item,
            parent, false
        )
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dateText = "Last Updated : " + allNotes[position].timeStamp
        holder.noteTV.text = allNotes[position].noteTitle
        holder.dateTV.text = dateText
        holder.deleteIV.setOnClickListener {
            noteClickDeleteInterface.onDeleteIconClick(allNotes[position])
        }
        holder.itemView.setOnClickListener {
            noteClickInterface.onNoteClick(allNotes[position])
        }
    }

    override fun getItemCount(): Int {
        return allNotes.size
    }

    fun updateList(newList: List<Notes>) {
        allNotes.clear()
        allNotes.addAll(newList)
        notifyDataSetChanged()
    }
    interface NotesClickDeleteInterface{
        fun onDeleteIconClick(notes: Notes)
    }

    interface NotesClickInterface {
        fun onNoteClick(notes: Notes)
    }
}

