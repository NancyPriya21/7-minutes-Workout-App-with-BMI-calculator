package com.example.a7_minuteworkout

import android.app.Application

class WorkoutApp: Application() {
    val db by lazy {
        HistoryDatabase.getInstance(this)
    }
}
//set the manifest
/*storing the exercise that we finished in our database, so storing it through finishActivity
  at end of workout i.e. addDateToDatabase function */