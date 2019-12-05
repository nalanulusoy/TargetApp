package com.example.targetapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.launch

class AddNewTargetViewModel (application: Application) : AndroidViewModel(application) {

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

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(target: TargetModel) = viewModelScope.launch {
            repository.insert(target)
        }
}
