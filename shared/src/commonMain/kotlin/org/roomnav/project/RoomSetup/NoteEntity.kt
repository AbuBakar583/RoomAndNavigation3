package org.roomnav.project.RoomSetup

import androidx.room3.Entity
import androidx.room3.PrimaryKey
import kotlin.time.Clock
import kotlin.time.Clock.System


@Entity
data class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long  = 0,
    val title: String,
    val description: String,
    val updatedAt: Long  = System.now().toEpochMilliseconds()
)