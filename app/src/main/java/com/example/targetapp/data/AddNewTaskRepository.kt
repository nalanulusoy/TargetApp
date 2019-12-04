package com.example.targetapp

import androidx.lifecycle.LiveData

class AddNewTaskRepository(private val targetDao: TaskDao) {

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    val allTargets: LiveData<List<TargetModel>> = targetDao.getAllTargets()

    val allTargetsCompleted:  LiveData<List<TargetModel>> =targetDao.getCompletedTargets()

    suspend fun insert(target: TargetModel) {
        targetDao.insert(target)
    }
}