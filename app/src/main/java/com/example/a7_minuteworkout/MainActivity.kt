package com.example.a7_minuteworkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.a7_minuteworkout.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    //The binding is name just like the name of the layout with Binding attached
    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*added a dependency to build.gradle as it contains the class android.viewbinding.ViewBinding
         which AndroidStudio could not access. */
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        //val startBtn :FrameLayout =findViewById(R.id.flStart)
        binding?.flStart?.setOnClickListener{
            //Toast.makeText(this@MainActivity, "Here we will start the exercise.",Toast.LENGTH_SHORT).show()
            val intent = Intent(this, ExerciseActivity::class.java)
            startActivity(intent)
        }

        binding?.flBMI?.setOnClickListener{
            val intent = Intent(this, BmiActivity::class.java)
            startActivity(intent)
        }

        binding?.flHistory?.setOnClickListener{
            val intent = Intent(this, HistoryActivity::class.java)
            startActivity(intent)
        }
    }

    //reset binding to null again to avoid memory leaks
    override fun onDestroy() {
        super.onDestroy()
        binding = null  //To avoid memory leak we un assign the binding once the activity is destroyed
    }
}


