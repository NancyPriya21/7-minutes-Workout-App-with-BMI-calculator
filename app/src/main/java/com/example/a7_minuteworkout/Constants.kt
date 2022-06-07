package com.example.a7_minuteworkout

object Constants {

    fun defaultExerciseList(): ArrayList<ExerciseModel>{
        val exerciseList = ArrayList<ExerciseModel>()

        val jumpingJacks = ExerciseModel(1, "Jumping Jacks", R.drawable.exercise_jumping_jack,
            false, false)
        exerciseList.add(jumpingJacks)

        val wallSit = ExerciseModel(2, "Wall Sit", R.drawable.wall_sit,
            false, false)
        exerciseList.add(wallSit)

        val plank = ExerciseModel(3, "Plank", R.drawable.plank,
            false, false)
        exerciseList.add(plank)

        val sidePlank = ExerciseModel(4, "Side Plank", R.drawable.side_plank,
            false, false)
        exerciseList.add(sidePlank)

        val squats = ExerciseModel(5, "Squats", R.drawable.squats,
            false, false)
        exerciseList.add(squats)

        val lunges = ExerciseModel(6, "Lunges", R.drawable.lunges_exercise,
            false, false)
        exerciseList.add(lunges)

        val crunches = ExerciseModel(7, "Abdominal Crunches", R.drawable.crunches_abdominals,
            false, false)
        exerciseList.add(crunches)

        val runOnSpot = ExerciseModel(8, "High Knee Run On Spot", R.drawable.high_knees_front_knee_runon_the_spot,
            false, false)
        exerciseList.add(runOnSpot)

        val stepOnChair = ExerciseModel(9, "Step Up On Chair", R.drawable.step_up_on_chair,
            false, false)
        exerciseList.add(stepOnChair)

        val tricepsDipOnChair = ExerciseModel(10, "Triceps Dip On Chair", R.drawable.triceps_dip_on_chair,
            false, false)
        exerciseList.add(tricepsDipOnChair)

        val pushUps = ExerciseModel(11, "Pushups", R.drawable.pushup,
            false, false)
        exerciseList.add(pushUps)

        val pushUpAndRotate = ExerciseModel(12, "pushUpAndRotate", R.drawable.pushup_and_rotate,
            false, false)
        exerciseList.add(pushUpAndRotate)

        return exerciseList
    }
}