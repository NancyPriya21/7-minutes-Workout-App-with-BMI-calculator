package com.example.a7_minuteworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.example.a7_minuteworkout.databinding.ActivityFinishBinding
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class FinishActivity : AppCompatActivity() {

    private var binding: ActivityFinishBinding? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFinishBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setSupportActionBar(binding?.toolbarFinishActivity)

        if (supportActionBar!=null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        binding?.toolbarFinishActivity?.setNavigationOnClickListener {
            onBackPressed()
        }

        binding?.btnFinish?.setOnClickListener {
            finish()
        }

        val dao = (application as WorkoutApp).db.historyDao()
        //here historyDao() is the abstract function in HistoryDatabase
        addDateToDatabase(dao)
    }

    private fun addDateToDatabase(historyDao: HistoryDao){

        val myCalender = Calendar.getInstance()
        val dateTime = myCalender.time
        Log.e("Date: ", ""+dateTime)

        //Locale.getDefault() shall take time as per the mobile settings locale
        val sdf= SimpleDateFormat("dd MMM yyyy HH:mm:ss", Locale.getDefault())
        val date = sdf.format(dateTime)      //converting date format to string
        Log.e("Formatted Date: ",""+date)

        lifecycleScope.launch {
           historyDao.insert(HistoryEntity(date))
            Log.e("Date: ", "Added..")
        }
    }
}