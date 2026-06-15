package org.roomnav.project

import androidx.room3.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import org.roomnav.project.RoomSetup.AppDatabase
import java.io.File


fun createDatabase(): AppDatabase {
    val dbFile = File(System.getProperty("user.home"), "navnotes.db")
    return Room.databaseBuilder<AppDatabase>(name = dbFile.absolutePath)
        .setDriver(BundledSQLiteDriver())
        .build()
}