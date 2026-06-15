package org.roomnav.project

import android.content.Context
import androidx.room3.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import org.roomnav.project.RoomSetup.AppDatabase


fun createDatabase(context: Context): AppDatabase {
    val dbFile = context.applicationContext.getDatabasePath("navnotes.db")
    return Room.databaseBuilder<AppDatabase>(
        context = context.applicationContext,
        name = dbFile.absolutePath,
    )
        .setDriver(BundledSQLiteDriver())
        .build()
}