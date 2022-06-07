package com.example.a7_minuteworkout

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.a7_minuteworkout.databinding.ItemExerciseStatusBinding

class ExerciseStatusAdapter(private val items: ArrayList<ExerciseModel>)
    :RecyclerView.Adapter <ExerciseStatusAdapter.ViewHolder> () {

    //since we are using viewBinding, passing the item_exercise_status xml for binding to ViewHolder
    //A ViewHolder describes an item view and metadata about its place within the RecyclerView.
    //A binding variable is created in its constructor for the item layout
    inner class ViewHolder(binding: ItemExerciseStatusBinding): RecyclerView.ViewHolder(binding.root){
            val textViewItem = binding.tvItem
    }

    //Inflates the item views which is designed in xml layout file
    //create a new {@link ViewHolder} and initializes some private fields to be used by RecyclerView.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        //RecyclerView.Adapter does not have its own context, so its context is taken from its parent
        //we are inflating a viewHolder here, need to take it from parent as this is a class and not an activity
        return ViewHolder(ItemExerciseStatusBinding.inflate(LayoutInflater.from(parent.context),
            parent, false))
    }

    // Binds each item in the ArrayList to a view
    // Called when RecyclerView needs a new {@link ViewHolder} of the given type to represent an item.
    // This new ViewHolder should be constructed with a new View that can represent the items of the given type.
    // You can either create a new View manually or inflate it from an XML layout file.
    // onBindViewHolder helps us to decide what to do with each item in the binding
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //position represents the position of our current item in RV
        val model: ExerciseModel = items[position]
        holder.textViewItem.text= model.getId().toString()
        when{
            model.getIsSelected() ->{
                //itemView is the view of the recycleView ViewHolder
                holder.textViewItem.background = ContextCompat.getDrawable(holder.itemView.context,
                    R.drawable.item_circular_color_white_background)
                holder.textViewItem.setTextColor(Color.parseColor("#212121"))
            }
            model.getIsCompleted() ->{
                holder.textViewItem.background = ContextCompat.getDrawable(holder.itemView.context,
                    R.drawable.circular_item_green_background)
                holder.textViewItem.setTextColor(Color.parseColor("#FFFFFF"))
            }
            else -> {
                holder.textViewItem.background = ContextCompat.getDrawable(holder.itemView.context,
                    R.drawable.item_circular_color_grey_background)
                holder.textViewItem.setTextColor(Color.parseColor("#212121"))
            }
        }
    }

    //Gets the number of items in the list
    override fun getItemCount(): Int {
         return items.size
    }
}