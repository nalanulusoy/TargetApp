package com.example.targetapp.ui.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.targetapp.AddNewTargetRepository
import com.example.targetapp.TargetModel
import com.example.targetapp.TargetRoomDatabase
import io.reactivex.disposables.CompositeDisposable


class HomeTargetListViewModel (application: Application) : AndroidViewModel(application) {


    // The ViewModel maintains a reference to the repository to get data.
    private val repository: AddNewTargetRepository
    // LiveData gives us updated target when they change.
    val allTargets: LiveData<List<TargetModel>>

    var comp : CompositeDisposable
    init {
        val targetDao = TargetRoomDatabase.getDatabase(application, viewModelScope).taskDao()
        comp=CompositeDisposable()
        repository = AddNewTargetRepository(targetDao,comp)
        allTargets = repository.allTargets()

    }


}