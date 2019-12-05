

package com.example.targetapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch



@Database(entities = [TargetModel::class], version = 7, exportSchema = false)
abstract class TargetRoomDatabase : RoomDatabase() {

    abstract fun taskDao(): TargetDao

    companion object {
        @Volatile
        private var INSTANCE: TargetRoomDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): TargetRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TargetRoomDatabase::class.java,
                    "target_database"
                )
                    .fallbackToDestructiveMigration()
                    .addCallback(TargetDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }

        private class TargetDatabaseCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {

            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {

                    }
                }
            }
        }
    }




}
