package org.roomnav.project.RoomSetup

import androidx.room3.Dao
import androidx.room3.Insert
import androidx.room3.Query
import androidx.room3.Update
import kotlinx.coroutines.flow.Flow


@Dao
interface NoteDao {
    @Query("SELECT * FROM NoteEntity ORDER BY updatedAt DESC")
    fun observeAllNotes(): Flow<List<NoteEntity>>

    @Insert
    suspend fun insertNote(note: NoteEntity): Long

    @Update
    suspend fun updateNote(note: NoteEntity)

    @Query("DELETE FROM NoteEntity WHERE id = :id")
    suspend fun deleteById(id: Long)


}