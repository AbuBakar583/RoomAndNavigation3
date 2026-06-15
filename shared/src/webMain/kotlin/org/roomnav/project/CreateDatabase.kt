package org.roomnav.project

import androidx.room3.Room
import org.roomnav.project.RoomSetup.AppDatabase

fun createDatabase(): AppDatabase {
    return Room.databaseBuilder<AppDatabase>("navnotes.db")
        .setDriver(createWebWorkerDriver())
        .setSingleConnectionPool()
        .build()
}
