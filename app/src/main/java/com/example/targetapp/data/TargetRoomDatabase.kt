/*
 * Copyright (C) 2017 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.targetapp

import android.content.Context
import android.os.Build
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDateTime


@Database(entities = [TargetModel::class], version = 5, exportSchema = false)
abstract class TargetRoomDatabase : RoomDatabase() {

    abstract fun taskDao(): TaskDao

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
            /**
             * Override the onOpen method to populate the database.
             * For this sample, we clear the database every time it is created or opened.
             */
            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                // If you want to keep the data through app restarts,
                // comment out the following line.
                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
                        populateDatabase(database.taskDao())
                    }
                }
            }
        }

        /**
         * Populate the database in a new coroutine.
         * If you want to start with more targets, just add them.
         */
        suspend fun populateDatabase(taskDao: TaskDao) {
            // Start the app with a clean database every time.
            // Not needed if you only populate on creation.
            taskDao.deleteAll()
            var target = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                TargetModel("Coding Project",true, 1575463920569)
            } else {
                TODO("VERSION.SDK_INT < O")
            }
            taskDao.insert(target)
            target = TargetModel("Push Github",false,1575463920569)
            taskDao.insert(target)
        }
    }


}
