package com.example.targetapp

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE

@Dao
interface TaskDao {


    @Query("SELECT * from target_table ORDER BY id")
    fun getAllTargets(): LiveData<List<TargetModel>>

    @Insert(onConflict = REPLACE)
    fun insert(vararg target: TargetModel)

    @Query("DELETE FROM target_table")
    suspend fun deleteAll()

    @Query("UPDATE target_table SET target =:targetName, targetStatus =:targetStatus, targetDate =:targetDate WHERE id =:targetID")
    fun update(targetID: Int, targetName:String, targetStatus:Boolean, targetDate:Long)


    @Query("select * from target_table where id =:rowId")
    fun findTargetById(rowId: Int):LiveData<TargetModel>



    @Query("SELECT * FROM target_table WHERE targetStatus =1")
    fun getCompletedTargets(): LiveData<List<TargetModel>>
}