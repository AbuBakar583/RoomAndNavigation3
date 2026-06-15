package org.roomnav.project

import androidx.compose.ui.window.ComposeUIViewController
import org.roomnav.NotesScreenModel
import org.roomnav.project.RoomSetup.NotesRepository

fun MainViewController() = ComposeUIViewController {

    val db = createDatabase()
    val repo = NotesRepository(db.noteDao())
    val model = NotesScreenModel(repo)
    App(model)


}