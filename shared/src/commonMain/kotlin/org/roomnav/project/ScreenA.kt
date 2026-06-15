package org.roomnav.project

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.roomnav.NotesScreenModel
import org.roomnav.project.RoomSetup.NoteEntity


@Composable
fun ScreenA(model: NotesScreenModel, goToScreenB: () -> Unit = {}) {
    val notes by model.notes.collectAsState()

    var editingId by remember { mutableStateOf<Long?>(null) }
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("This is Screen A")
        Spacer(modifier = Modifier.size(50.dp))
        Button(onClick = { goToScreenB() }) {
            Text("Go to Screen B")
        }
        Column(modifier = Modifier.padding(16.dp)) {
            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Title") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Description") },
                modifier = Modifier.fillMaxWidth()
            )

            Row(modifier = Modifier.padding(top = 8.dp)) {
                Button(onClick = {
                    if (title.isBlank()) return@Button
                    val id = editingId
                    if (id == null) model.add(title, description)
                    else model.update(id, title, description)

                    editingId = null
                    title = ""
                    description = ""
                }) {
                    Text(if (editingId == null) "Add" else "Save")
                }

                Button(
                    onClick = {
                        editingId = null
                        title = ""
                        description = ""
                    },
                    modifier = Modifier.padding(start = 8.dp)
                ) {
                    Text("Clear")
                }
            }

            LazyColumn(modifier = Modifier.padding(top = 16.dp)) {
                items(notes, key = { it.id }) { note ->
                    NoteRow(
                        note = note,
                        onEdit = {
                            editingId = note.id
                            title = note.title
                            description = note.description
                        },
                        onDelete = {
                            model.delete(note.id)
                        }
                    )
                }
            }
        }
    }


}

@Composable
private fun NoteRow(
    note: NoteEntity,
    onEdit: () -> Unit,
    onDelete: () -> Unit,
)
{
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(note.title)
            Text(note.description)

            Row(modifier = Modifier.padding(top = 8.dp)) {
                Button(onClick = onEdit) { Text("Edit") }
                Button(
                    onClick = onDelete,
                    modifier = Modifier.padding(start = 8.dp)
                ) {
                    Text("Delete")
                }
            }
        }
    }



}