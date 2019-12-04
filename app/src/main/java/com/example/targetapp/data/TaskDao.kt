package com.example.targetapp

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TaskDao {

    @Query("SELECT * from target_table ORDER BY target ASC")
    fun getAllTargets(): LiveData<List<TargetModel>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(vararg target: TargetModel)

    @Query("DELETE FROM target_table")
    suspend fun deleteAll()


    @Update
    suspend fun update(vararg todos: TargetModel)


    @Query("select * from target_table where id =:rowId")
    fun findTargetById(rowId: Int):LiveData<TargetModel>


    @Query("SELECT * FROM target_table WHERE targetStatus =1")
    fun getCompletedTargets(): LiveData<List<TargetModel>>
}