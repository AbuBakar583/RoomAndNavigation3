package org.roomnav.project.RoomSetup

import kotlinx.coroutines.flow.Flow


class NotesRepository(
    private val dao: NoteDao,
) {

    fun observeAllNotes(): Flow<List<NoteEntity>> = dao.observeAllNotes()

    suspend fun addTitle(title: String, description: String) {
        dao.insertNote(NoteEntity(title = title, description = description))
    }

    suspend fun updateNote(id: Long, title: String, description: String) {
        dao.updateNote(NoteEntity(id = id, title = title, description = description))
    }

    suspend fun deleteNote(id: Long) {
        dao.deleteById(id)
    }
}