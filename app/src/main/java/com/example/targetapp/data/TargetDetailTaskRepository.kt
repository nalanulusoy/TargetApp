package com.example.targetapp.data

import com.example.targetapp.TargetModel
import com.example.targetapp.TaskDao

class TargetDetailTaskRepository (private val targetDao: TaskDao) {


    suspend fun update(target: TargetModel) {
        targetDao.update(target)
    }
}