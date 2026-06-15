package org.roomnav.project.RoomSetup

import androidx.room3.ConstructedBy
import androidx.room3.Database
import androidx.room3.Room
import androidx.room3.RoomDatabase
import androidx.room3.RoomDatabaseConstructor
@Database(entities = [NoteEntity::class], version = 1)
@ConstructedBy(AppDatabaseConstructor::class)
abstract class AppDatabase : RoomDatabase(){
    abstract fun noteDao(): NoteDao

}
@Suppress("KotlinNoActualForExpect")
expect object AppDatabaseConstructor: RoomDatabaseConstructor<AppDatabase>{
    override fun initialize(): AppDatabase


}