package com.example.a7_minuteworkout

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface HistoryDao {
    @Insert
    suspend fun insert (historyEntity: HistoryEntity) //Note: we shall insert data in finishActivity

    @Query("SELECT * FROM `History-table`")
    fun fetchAllDates():Flow<List<HistoryEntity>> //we will retrieve data in HistoryActivity
    //no need to add suspend prefix, flow shall take care of coroutines activities
}