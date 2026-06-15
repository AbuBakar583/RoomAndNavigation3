package org.roomnav.project

import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import org.roomnav.NotesScreenModel
import org.roomnav.project.RoomSetup.NotesRepository

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    ComposeViewport {
        val db = remember { createDatabase() }
        val repo = remember { NotesRepository(db.noteDao()) }
        val model = remember { NotesScreenModel(repo) }
        App(model)
    }
}
