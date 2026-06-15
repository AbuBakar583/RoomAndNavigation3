package org.roomnav

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.roomnav.project.RoomSetup.NoteEntity
import org.roomnav.project.RoomSetup.NotesRepository


class NotesScreenModel(
    private val repo: NotesRepository
){
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

    val notes: StateFlow<List<NoteEntity>> =
        repo.observeAllNotes().stateIn(
            scope = scope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList(),
        )

    fun add(title: String, description: String) {
        scope.launch { repo.addTitle(title, description) }
    }

    fun update(id: Long, title: String, description: String) {
        scope.launch { repo.updateNote(id, title, description) }
    }

    fun delete(id: Long) {
        scope.launch { repo.deleteNote(id) }
    }





}