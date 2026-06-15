package org.roomnav.project

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import org.roomnav.NotesScreenModel
import org.roomnav.project.RoomSetup.NotesRepository

fun main() = application {

    val db = createDatabase()
    val repo = NotesRepository(db.noteDao())
    val model = NotesScreenModel(repo)
    Window(
        onCloseRequest = ::exitApplication,
        title = "RoomAndNavigation3",
    ) {
        App(model)
    }
}