package com.example.a7_minuteworkout

class ExerciseModel(private var id:Int, private var name:String, private var image:Int,
    private var isSelected:Boolean,
    private var isCompleted:Boolean)
{
    fun getId():Int{
        return id
    }
    fun setId(passedId: Int){
        this.id=passedId
    }
    fun getName():String{
        return name
    }
    fun setName(passedName: String){
        this.name=passedName
    }
    fun getImage():Int{
        return image
    }
    fun setImage(passedImage: Int){
        this.image=passedImage
    }
    fun getIsSelected():Boolean{
        return isSelected
    }
    fun setIsSelected(passedIsSelected: Boolean){
        this.isSelected=passedIsSelected
    }
    fun getIsCompleted():Boolean{
        return isCompleted
    }
    fun setIsCompleted(passedIsCompleted: Boolean){
        this.isCompleted=passedIsCompleted
    }

}






































