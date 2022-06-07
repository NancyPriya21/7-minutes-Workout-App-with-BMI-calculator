package com.example.a7_minuteworkout

import android.app.Dialog
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a7_minuteworkout.databinding.ActivityExerciseBinding
import com.example.a7_minuteworkout.databinding.CustomDialogForBackConfirmationBinding
import java.util.*
import kotlin.collections.ArrayList

class ExerciseActivity : AppCompatActivity(), TextToSpeech.OnInitListener {

    private var binding : ActivityExerciseBinding? = null
    private var restTimer: CountDownTimer?= null    //timer for how much time you want to rest/relax
    private var restProgress= 0                      //how far we have come/exercised

    private var exerciseTimer: CountDownTimer?= null    //timing a single exercise
    private var exerciseTimerProgress= 0

    private var exerciseList: ArrayList<ExerciseModel>?= null
    private var currentExercisePosition: Int = -1   //index for exerciseList

    private var tts: TextToSpeech?=null //added action in manifest for android 11.Android 12 onwards, not required
    private var player: MediaPlayer?= null //sound to be played when an exercise finishes

    private var exerciseAdapter : ExerciseStatusAdapter? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExerciseBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        //since we have removed the action bar in our app, adding a toolbar/navigation bar instead
        //setSupportActionBar: This method sets the toolbar as the app bar for the activity
        setSupportActionBar(binding?.exerciseToolbar)

        //adding a back button in the toolbar
        if (supportActionBar != null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        //actionBar.setDisplayHomeAsUpEnabled will make the icon clickable and add the <- at the left of the icon.

        }
        //setNavigationOnClickListener: Sets a listener to respond to navigation events.
        binding?.exerciseToolbar?.setNavigationOnClickListener {
            //onBackPressed()    //simulating the back button pressed by user.
            customDialogForBackButton()
        }

        exerciseList= Constants.defaultExerciseList()

        tts = TextToSpeech(this,this)

        setUpRestView()

        setUpExerciseStatusRecyclerView()

    }

    //function for the RecyclerView
    private fun setUpExerciseStatusRecyclerView(){
        //reverseLayout=  true, if the order of RV should be reversed or not
        binding?.rvExerciseStatus?.layoutManager = LinearLayoutManager(this,
            LinearLayoutManager.HORIZONTAL, false )
        if(exerciseList!=null){
        exerciseAdapter = ExerciseStatusAdapter(exerciseList!!)
        }
        binding?.rvExerciseStatus?.adapter= exerciseAdapter
    }

    private fun setUpRestView(){
        //Adding sound at the start of restTimer
        try{
            val soundURI = Uri.parse("android.resource://com.example.a7_minuteworkout/"
                    + R.raw.press_start)
            player =MediaPlayer.create(applicationContext, soundURI)
            player?.isLooping = false
            player?.start()
        }catch(e: Exception){
            e.printStackTrace()
        }

        binding?.flRestView?.visibility = View.VISIBLE
        binding?.tvTitle?.visibility =View.VISIBLE
        binding?.ExerciseImage?.visibility= View.INVISIBLE
        binding?.tvExerciseName?.visibility =View.INVISIBLE
        binding?.flExerciseView?.visibility= View.INVISIBLE
        binding?.tvUpcomingText?.visibility=View.VISIBLE
        binding?.tvUpcomingExercise?.visibility=View.VISIBLE

        //to make sure that we are not trying to run an already running timer
        if(restTimer!=null){
            restTimer?.cancel()
            restProgress=0
        }

        binding?.tvUpcomingExercise?.text= exerciseList!![currentExercisePosition+1].getName()
        setRestProgressBar()
    }

    private fun setUpExerciseView(){
        binding?.flRestView?.visibility = View.INVISIBLE //Hiding the restView from screen
        binding?.tvTitle?.visibility =View.INVISIBLE
        binding?.ExerciseImage?.visibility= View.VISIBLE
        binding?.tvExerciseName?.visibility =View.VISIBLE
        binding?.flExerciseView?.visibility= View.VISIBLE
        binding?.tvUpcomingText?.visibility=View.INVISIBLE
        binding?.tvUpcomingExercise?.visibility=View.INVISIBLE

        if(exerciseTimer!=null){
            exerciseTimer?.cancel()
            exerciseTimerProgress=0
        }

        speakOut(exerciseList!![currentExercisePosition].getName())

        binding?.ExerciseImage?.setImageResource(exerciseList!![currentExercisePosition].getImage())
        binding?.tvExerciseName?.text= exerciseList!![currentExercisePosition].getName()
        setExerciseProgressBar()
    }

    private fun setRestProgressBar(){
        binding?.progressBar?.progress = restProgress
        restTimer = object: CountDownTimer(5000, 1000){
            override fun onTick(p0: Long) {
                restProgress++
                binding?.progressBar?.progress = 5 - restProgress
                binding?.tvTimer?.text= (5 - restProgress).toString()
            }
            override fun onFinish() {
               //Toast.makeText(this@ExerciseActivity, "Let's start the exercise", Toast.LENGTH_SHORT).show()
                currentExercisePosition++
                //changing the drawable for selected viewHolder
                exerciseList!![currentExercisePosition].setIsSelected(true)
                exerciseAdapter!!.notifyDataSetChanged()
                setUpExerciseView()
            }
        }.start()
    }

    private fun setExerciseProgressBar(){
        binding?.progressBarExerciseView?.progress = exerciseTimerProgress
        exerciseTimer = object: CountDownTimer(30000, 1000){
            override fun onTick(p0: Long) {
                exerciseTimerProgress++
                binding?.progressBarExerciseView?.progress = 30 - exerciseTimerProgress
                binding?.tvExerciseTimerView?.text= (30 - exerciseTimerProgress).toString()
            }
            override fun onFinish() {
                //Toast.makeText(this@ExerciseActivity, "30 seconds are over, Let's Rest",Toast.LENGTH_SHORT).show()
              if(currentExercisePosition < exerciseList?.size!! -1) {

                  //setting the isSelected to false as we are moving to next exercise after rest time,
                  // and now the viewHolder exercise is completed, then setUpRestView() is called to increment position, order is imp here
                  exerciseList!![currentExercisePosition].setIsSelected(false)
                  exerciseList!![currentExercisePosition].setIsCompleted(true)
                  exerciseAdapter!!.notifyDataSetChanged()

                  setUpRestView()
              }
              else{
                  finish()
                  val intent= Intent(this@ExerciseActivity, FinishActivity::class.java )
                  startActivity(intent)
              }
            }
        }.start()
    }

    private fun customDialogForBackButton(){
        var customDialog =Dialog(this)
        //making a new binding for customDialogForBackConfirmation.xml as the existing binding is for activity_exercise
        val dialogBinding = CustomDialogForBackConfirmationBinding.inflate(layoutInflater)
        customDialog.setContentView(dialogBinding.root)
        customDialog.setCanceledOnTouchOutside(false)
        dialogBinding.btnYes.setOnClickListener {
            this@ExerciseActivity.finish()
            customDialog.dismiss()
        }
        dialogBinding.btnNo.setOnClickListener {
            customDialog.dismiss()
        }
        customDialog.show()
    }

    override fun onBackPressed() {
        //super.onBackPressed()
        customDialogForBackButton()
    }
    override fun onDestroy() {
        super.onDestroy()
        if(restTimer!=null){
            restTimer?.cancel()
            restProgress=0
        }
        if(exerciseTimer!=null){
            exerciseTimer?.cancel()
            exerciseTimerProgress=0
        }
        if(tts!=null){
            tts!!.stop()
            tts!!.shutdown()
        }
        if(player!=null){
            player!!.stop()
        }
        binding= null
    }

    override fun onInit(status: Int) {
        if(status == TextToSpeech.SUCCESS){
            //setting US english as tts language
            val result = tts?.setLanguage(Locale.US)
            if(result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED ){
                Log.e("TTS", "The language specified is not supported")
            }
        }
        else{
            Log.e("TTS", "Initialization failed")
        }
    }

    private fun speakOut(text: String){
        if(tts!=null) {
            tts?.speak(text, TextToSpeech.QUEUE_FLUSH, null, "")
        }
    }
}
