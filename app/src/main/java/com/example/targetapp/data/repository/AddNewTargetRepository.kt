package com.example.targetapp

import androidx.lifecycle.LiveData

import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class AddNewTargetRepository(private val targetDao: TargetDao, compositeDisposable: CompositeDisposable) {

    var comp : CompositeDisposable = compositeDisposable


    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.


    fun allTargets():LiveData<List<TargetModel>>{

        return targetDao.getAllTargets()

    }

       fun insert(target: TargetModel) {
        comp.add(Observable.fromCallable { targetDao.insert(target)}
            .subscribeOn(Schedulers.computation())
            .subscribe())

    }
}