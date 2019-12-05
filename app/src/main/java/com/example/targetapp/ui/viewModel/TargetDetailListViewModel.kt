package com.example.targetapp.ui.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.room.OnConflictStrategy
import androidx.room.Update

import com.example.targetapp.TargetModel
import com.example.targetapp.TargetRoomDatabase
import com.example.targetapp.TaskDao
import com.example.targetapp.data.repository.TargetDetailTaskRepository
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.launch


class TargetDetailListViewModel(application: Application) : AndroidViewModel(application) {

    // The ViewModel maintains a reference to the repository to get data.

    // LiveData gives us updated target when they change.
    lateinit var allTargets: LiveData<TargetModel>

    var targetDao: TaskDao
    var comp: CompositeDisposable


    var targetDetailRepository: TargetDetailTaskRepository


    init {
        targetDao = TargetRoomDatabase.getDatabase(application, viewModelScope).taskDao()
        comp = CompositeDisposable()
        targetDetailRepository =
            TargetDetailTaskRepository(targetDao, comp)

    }

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateTarget(targetModel: TargetModel, targetId: Int) = viewModelScope.launch {
        targetDetailRepository.update(targetModel, targetId)

    }


    fun findByIdTarget(id: Int): LiveData<TargetModel> {
        allTargets = targetDao.findTargetById(id)
        return allTargets
    }


}


