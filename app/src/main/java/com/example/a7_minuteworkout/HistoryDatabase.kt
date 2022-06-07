package com.example.a7_minuteworkout

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [HistoryEntity::class], version = 1)
abstract class HistoryDatabase: RoomDatabase() {

    abstract fun historyDao(): HistoryDao

    companion object{

        /*Marks the JVM backing field of the annotated property as volatile,
         meaning that writes to this field are immediately made visible to other threads. */
        @Volatile
        private var Instance : HistoryDatabase? =null

        fun getInstance(context: Context): HistoryDatabase{
              synchronized(this){
                  //local Instance will be the existing instance
                  var instance = Instance

                  //if instance is null, we are building a new one
                  if(instance==null){
                      instance= Room.databaseBuilder(context.applicationContext, HistoryDatabase::class.java,
                      "history_database").fallbackToDestructiveMigration().build()
                  Instance= instance
                  }
              return instance
              }
        }
    }
}