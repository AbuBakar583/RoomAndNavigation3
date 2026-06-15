package org.roomnav.project

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import org.roomnav.NotesScreenModel
import org.roomnav.project.RoomSetup.NotesRepository

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        val db = createDatabase(this)
        val repo = NotesRepository(db.noteDao())
        val model = NotesScreenModel(repo)

        setContent {
            App(model)
        }
    }
}
