package com.example.targetapp.ui.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.targetapp.AddNewTaskRepository
import com.example.targetapp.TargetModel
import com.example.targetapp.TargetRoomDatabase


class HomeTaskListViewModel (application: Application) : AndroidViewModel(application) {


    // The ViewModel maintains a reference to the repository to get data.
    private val repository: AddNewTaskRepository
    // LiveData gives us updated words when they change.
    val allTargets: LiveData<List<TargetModel>>

    init {
        val targetDao = TargetRoomDatabase.getDatabase(application, viewModelScope).taskDao()
        repository = AddNewTaskRepository(targetDao)
        allTargets = repository.allTargets

    }


}