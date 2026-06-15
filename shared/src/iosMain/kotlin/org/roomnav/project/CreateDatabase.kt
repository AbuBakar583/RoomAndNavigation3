package org.roomnav.project

import androidx.room3.Room
import kotlinx.cinterop.ExperimentalForeignApi
import org.roomnav.project.RoomSetup.AppDatabase
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSUserDomainMask


@OptIn(ExperimentalForeignApi::class)
fun createDatabase(): AppDatabase {
    val url = NSFileManager.defaultManager.URLForDirectory(
        directory = NSDocumentDirectory,
        inDomain = NSUserDomainMask,
        appropriateForURL = null,
        create = false,
        error = null
    )!!
    val dbPath = requireNotNull(url.path) + "/navnotes.db"

    return Room.databaseBuilder<AppDatabase>(name = dbPath)
        .setDriver(androidx.sqlite.driver.bundled.BundledSQLiteDriver())
        .build()
}