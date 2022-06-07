package com.example.a7_minuteworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a7_minuteworkout.databinding.ActivityHistoryBinding
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.collect

class HistoryActivity : AppCompatActivity() {

    private var binding: ActivityHistoryBinding?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setSupportActionBar(binding?.toolbarHistory)
        if(supportActionBar!=null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.title = "Workout History"
        }
        binding?.toolbarHistory?.setNavigationOnClickListener {
            onBackPressed()
        }

        //retrieving data to show in our activity
        val dao = (application as WorkoutApp).db.historyDao()
        getAllDates(dao)
    }

    private fun getAllDates(historyDao: HistoryDao){
        lifecycleScope.launch {
            //import kotlinx.coroutines.flow.collect to collect flow
            historyDao.fetchAllDates().collect{ allDatesList ->
                if (allDatesList.isNotEmpty()){
                    binding?.tvHistory?.visibility= View.VISIBLE
                    binding?.rvHistory?.visibility= View.VISIBLE
                    binding?.tvNoDataAvailable?.visibility= View.GONE

                    binding?.rvHistory?.layoutManager = LinearLayoutManager(this@HistoryActivity)
                    val datesToBeDisplayed = ArrayList<String>()
                    for(i in allDatesList){
                        datesToBeDisplayed.add(i.date)
                    }
                    val historyAdapter = HistoryAdapter(datesToBeDisplayed)
                    binding?.rvHistory?.adapter = historyAdapter //assigning adapter to a recyclerView
                }
                else{
                    binding?.tvHistory?.visibility= View.GONE
                    binding?.rvHistory?.visibility= View.GONE
                    binding?.tvNoDataAvailable?.visibility= View.VISIBLE
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        // reset the binding to null to avoid memory leak
        binding = null
    }

}


