package com.example.a7_minuteworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.a7_minuteworkout.databinding.ActivityBmiBinding
import java.math.BigDecimal
import java.math.RoundingMode

class BmiActivity : AppCompatActivity() {

    //creating a companion object to check which radio btn is active
    companion object{
        /*companion object can be used to make its member static so it can be accessed by our class
        without creating a new instance of the class or here accessing the view by "binding?.view"
        Note: Kotlin does not have a static concept*/
        private const val MetricsUnitsView = "MetricsUnitsView"
        private const val USUnitsView = "USUnitsView"
    }

    //variable to hold the selected view visible
    private var currentVisibleView :String = MetricsUnitsView

    private var binding: ActivityBmiBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBmiBinding.inflate(layoutInflater)
        setContentView(binding?.root)


        setSupportActionBar(binding?.toolbarBmiActivity)
        supportActionBar?.setDisplayHomeAsUpEnabled(true) //set back button
        supportActionBar?.title = "CALCULATE BMI" // Setting a title in the action bar.
        binding?.toolbarBmiActivity?.setNavigationOnClickListener {
            onBackPressed()
        }

        makeMetricUnitVisible() //making metricSystem as our default radio btn

        binding?.rgUnits?.setOnCheckedChangeListener { _,checkedID:Int ->
            if(checkedID == R.id.rbMetricUnits){
                makeMetricUnitVisible()
            }
            else{
                makeUSUnitsVisible()
            }
        }

        binding?.btnCalculateUnits?.setOnClickListener {
            calculateBMI()
        }
    }

    //first step should be to check if appropriate values are given to editTexts
    private fun validateMetricsUnits(): Boolean{
        var isValid =true
        if(binding?.etMetricUnitWeight?.text.toString().isEmpty()){
            isValid =false
        }
        if(binding?.etMetricUnitHeight?.text.toString().isEmpty()){
            isValid =false
        }
        return isValid
    }

    private fun validateUSUnits(): Boolean{
        var isValid =true
        if(binding?.etUsMetricUnitWeight?.text.toString().isEmpty()){
            isValid =false
        }
        if(binding?.etUsMetricUnitHeightFeet?.text.toString().isEmpty()){
            isValid =false
        }
        else if(binding?.etUsMetricUnitHeightInch?.text.toString().isEmpty()){
            isValid =false
        }
        return isValid
    }

    private fun displayBmiResult(bmi: Float){
        val bmiType: String
        val bmiDescription: String

        if(bmi <= 15f){
            bmiType = "Very severely Underweight"
            bmiDescription = "Oops! You really need to take care of your body. Get a nutritious diet regularly"
        }
        else if(bmi > 15f && bmi<=16f){
            bmiType = "Severely Underweight"
            bmiDescription = "Oops! You really need to take care of your body. Get a nutritious diet regularly"
        }
        else if(bmi>16f && bmi<=18.5f){
            bmiType = "Underweight"
            bmiDescription = "Oops! You really need to take care of your body. Get a nutritious diet regularly"
        }
        else if(bmi>18.5f && bmi<=25f){
            bmiType = "Normal"
            bmiDescription = "Awesome! You are in a good shape"
        }
        else if(bmi>25f && bmi<=30f){
            bmiType = "Overweight"
            bmiDescription = "Oops! You need to take care of your body. Exercise regularly"
        }
        else if(bmi>30f && bmi<=35f){
            bmiType = "Obese class I. Moderately Obese"
            bmiDescription = "Oops! You need to take care of your body. Exercise regularly"
        }
        else if(bmi>35f && bmi<=40f){
            bmiType = "Obese class II. Severely Obese"
            bmiDescription = "Omg! You need to take care of your health. Act now"
        }
        else{
            bmiType = "Obese class III. Very Severely Obese"
            bmiDescription = "Omg! You need to take care of your health. Act now"
        }
        /*The BigDecimal class provides operations on double numbers for arithmetic, scale handling,
          rounding, comparison, format conversion and hashing. It can handle very large and very
          small floating point numbers with great precision*/
        val bmiValue = BigDecimal(bmi.toDouble()).setScale(2, RoundingMode.HALF_EVEN).toString()
        //eg. 2.5 will be converted to 2, 5.5 converted to 6

        binding?.llDiplayBMIResult?.visibility = View.VISIBLE //making all the textViews visible

        binding?.tvBMIValue?.text =bmiValue
        binding?.tvBMIType?.text = bmiType
        binding?.tvBMIDescription?.text = bmiDescription
    }

    private fun makeMetricUnitVisible(){
        currentVisibleView= MetricsUnitsView

        binding?.tilMetricUnitWeight?.visibility = View.VISIBLE // METRIC  Height UNITS VIEW is Visible
        binding?.tilMetricUnitHeight?.visibility = View.VISIBLE // METRIC  Weight UNITS VIEW is Visible
        binding?.tilUsMetricUnitWeight?.visibility = View.GONE // make weight view Gone.
        binding?.tilMetricUsUnitHeightFeet?.visibility = View.GONE // make height feet view Gone.
        binding?.tilMetricUsUnitHeightInch?.visibility = View.GONE // make height inch view Gone.

        binding?.etMetricUnitHeight?.text!!.clear() // height value is cleared if it is added.
        binding?.etMetricUnitWeight?.text!!.clear() // weight value is cleared if it is added.

        binding?.llDiplayBMIResult?.visibility = View.INVISIBLE
    }

    private fun makeUSUnitsVisible(){
        currentVisibleView= USUnitsView

        binding?.tilMetricUnitHeight?.visibility = View.INVISIBLE // METRIC  Height UNITS VIEW is InVisible
        binding?.tilMetricUnitWeight?.visibility = View.INVISIBLE // METRIC  Weight UNITS VIEW is InVisible
        binding?.tilUsMetricUnitWeight?.visibility = View.VISIBLE // make weight view visible.
        binding?.tilMetricUsUnitHeightFeet?.visibility = View.VISIBLE // make height feet view visible.
        binding?.tilMetricUsUnitHeightInch?.visibility = View.VISIBLE // make height inch view visible.

        binding?.etUsMetricUnitWeight?.text!!.clear() // weight value is cleared.
        binding?.etUsMetricUnitHeightFeet?.text!!.clear() // height feet value is cleared.
        binding?.etUsMetricUnitHeightInch?.text!!.clear() // height inch is cleared.

        binding?.llDiplayBMIResult?.visibility = View.INVISIBLE
    }

    private fun calculateBMI(){
        if(currentVisibleView== MetricsUnitsView) {
            if (validateMetricsUnits()) {
                val height: Float = binding?.etMetricUnitHeight?.text.toString()
                    .toFloat() / 100 //converting cm to m
                val weight: Float = binding?.etMetricUnitWeight?.text.toString().toFloat()
                val bmi = weight / (height * height)
                displayBmiResult(bmi)
            } else {
                Toast.makeText(this, "Please Fill all the fields", Toast.LENGTH_LONG)
                    .show()
            }
        }
        else{
            if (validateUSUnits()) {
                val heightInFeetUS: Float = binding?.etUsMetricUnitHeightFeet?.text.toString().toFloat()
                val heightInInchesUS: Float = binding?.etUsMetricUnitHeightInch?.text.toString().toFloat()
                val weightInUS: Float = binding?.etUsMetricUnitWeight?.text.toString().toFloat()

                //1 foot=12 inches
                val heightInUS= (heightInFeetUS*12) + heightInInchesUS
                val bmi= 703* weightInUS/(heightInUS*heightInUS)
                displayBmiResult(bmi)
            }
            else {
                Toast.makeText(this, "Please Fill all the fields", Toast.LENGTH_LONG)
                    .show()
            }
        }
    }
}