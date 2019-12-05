package com.example.targetapp.ui.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.room.OnConflictStrategy
import androidx.room.Update

import com.example.targetapp.TargetModel
import com.example.targetapp.TargetRoomDatabase
import com.example.targetapp.TargetDao
import com.example.targetapp.data.repository.TargetDetailTargetRepository
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.launch


class TargetDetailListViewModel(application: Application) : AndroidViewModel(application) {

    // The ViewModel maintains a reference to the repository to get data.

    // LiveData gives us updated target when they change.
    lateinit var allTargets: LiveData<TargetModel>

    var targetDao: TargetDao
    var comp: CompositeDisposable


    var targetDetailRepository: TargetDetailTargetRepository


    init {
        targetDao = TargetRoomDatabase.getDatabase(application, viewModelScope).taskDao()
        comp = CompositeDisposable()
        targetDetailRepository =
            TargetDetailTargetRepository(targetDao, comp)

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


