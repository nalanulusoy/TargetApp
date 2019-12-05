package com.example.targetapp.ui.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.targetapp.AddNewTaskRepository
import com.example.targetapp.TargetModel
import com.example.targetapp.TargetRoomDatabase
import io.reactivex.disposables.CompositeDisposable


class HomeTaskListViewModel (application: Application) : AndroidViewModel(application) {


    // The ViewModel maintains a reference to the repository to get data.
    private val repository: AddNewTaskRepository
    // LiveData gives us updated target when they change.
    val allTargets: LiveData<List<TargetModel>>

    var comp : CompositeDisposable
    init {
        val targetDao = TargetRoomDatabase.getDatabase(application, viewModelScope).taskDao()
        comp=CompositeDisposable()
        repository = AddNewTaskRepository(targetDao,comp)
        allTargets = repository.allTargets()

    }


}