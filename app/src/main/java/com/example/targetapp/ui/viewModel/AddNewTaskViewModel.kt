package com.example.targetapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import kotlinx.coroutines.launch

class AddNewTaskViewModel (application: Application) : AndroidViewModel(application) {

    // The ViewModel maintains a reference to the repository to get data.
    private val repository: AddNewTaskRepository
    // LiveData gives us updated words when they change.
    val allTargets: LiveData<List<TargetModel>>

    init {
        val targetDao = TargetRoomDatabase.getDatabase(application, viewModelScope).taskDao()
        repository = AddNewTaskRepository(targetDao)
        allTargets = repository.allTargets
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(target: TargetModel) = viewModelScope.launch {
            repository.insert(target)
        }
}
