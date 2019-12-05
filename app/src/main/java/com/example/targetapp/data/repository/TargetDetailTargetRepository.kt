package com.example.targetapp.data.repository

import com.example.targetapp.TargetModel
import com.example.targetapp.TargetDao
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class TargetDetailTargetRepository (private val targetDao: TargetDao, compositeDisposable: CompositeDisposable) {

    var comp : CompositeDisposable = compositeDisposable

    suspend fun update(target: TargetModel,targetId:Int) {
        comp.add(Observable.fromCallable {
         targetDao.update(targetId,target.target,target.targetStatus,target.targetDate) }
            .subscribeOn(Schedulers.computation())
            .subscribe())

    }
}