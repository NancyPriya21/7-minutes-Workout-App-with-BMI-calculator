package com.example.a7_minuteworkout

class Notes {
    /*
     View binding is a feature that allows you to more easily write code that interacts with views.
     it creates a binding class for every layout that holds the references of the views in the layout.
     Once view binding is enabled in a module, -> added buildFeatures in build gradle -> it generates a
     binding class for each XML layout file present in that module. An instance of a binding class
     contains direct references to all views that have an ID in the corresponding layout.
     In most cases, view binding replaces findViewById.
*/

/*
     Advantages of ViewBinding : the implementation is shorter and concise.
     It is faster and more efficient during compile time.
     It is a safer alternative because it ensures only the views from connected layout are being referenced.
     FindViewById could access views from all layouts if they have same id assigned to them.
*/

/*
    Inflate means reading a layout XML (often given as parameter) to translate them in Java code.
    "Inflating" a view means taking the layout XML and parsing it to create the view and viewGroup objects
     from the elements and their attributes specified within, and then adding the hierarchy of those views
     and viewGroups to the parent ViewGroup.
*/
/*circular progress Bar
  LayerList in xml for drawables: A Drawable that manages an array of other Drawables.
  These are drawn in array order, so the element with the largest index is be drawn on top.

  android:innerRadiusRatio(Float): The radius for the inner part of the ring, expressed as a ratio of
  the ring's width. For instance, if android:innerRadiusRatio="5", then the inner radius equals the ring's
  width divided by 5. This value is overridden by android:innerRadius. Default value is 9.

  android:thicknessRatio(Float) : The thickness of the ring, expressed as a ratio of the ring's width.
  For instance, if android:thicknessRatio="2", then the thickness equals the ring's width divided by 2.
  This value is overridden by android:innerRadius. Default value is 3.

  android:useLevel(Boolean): "true" if this is used as a LevelListDrawable. This should normally be "false"
  or your shape may not appear. <!-- in circular progress bar grey drawable : to get the grey background set
  it to false-->

  LevelListDrawable: A resource that manages a number of alternate Drawables, each assigned a maximum numerical
  value. Setting the level value of the object with Drawable.setLevel(int) will load the image with the next greater
  or equal value assigned to its max attribute. A good example use of a LevelListDrawable would be a battery
  level indicator icon, with different images to indicate the current battery level.
 */

/* indeterminate : we have set the indeterminate as false in ExerciseActivity.xml for progress bar because we know
the duration of our progress bar. It will either take 10 seconds or 30 seconds, which is why we set to terminate
setting to false.
use indeterminate mode for the Progress Bar when you do not know how long an operation will take.
 */
}

/* In Android, Adapter is a bridge between UI component and data source that helps us to fill data in UI component.
It holds the data and send the data to an Adapter view then view can takes the data from the adapter view
and shows the data on different views like as ListView, GridView, Spinner etc.

We assign an adapter to a recyclerView

Android Adapter is the connector used to connect the data source or database with our created views. Whatever the
data set you have, you can directly reflect that dataset using a specific Adapter View. In Android, we have
several types of Adapter Views: List View, Grid View, Spinners, Recycler View, etc.

-BaseAdapter – BaseAdapter is the parent adapter of all the available adapters.
-CursorAdapter – Using the CursorAdapter, you bind the data values quite quickly and efficiently.
-ArrayAdapter – ArrayAdapter is used to transfer the data in the form of an array and then use that array
                to insert data into the view.
-Custom ArrayAdapter – Custom Array Adapter allows you to customize the type of the array based on your needs.
                       You can even use Modal classes to create your custom ArrayAdapter.
-SimpleAdapter – SimpleAdapter allows you to keep static data from XML to the views.
Custom SimpleAdapter – Using the custom SimpleAdapter, you can customize the data items views and provide
                       access to each item.
*/

/*
-RecyclerView: combination of ListView & GridView. It is essentially a viewGroup of containers called ViewHolders
               which populate a particular item.
               viewHolders -> adapter -> dataSet ; viewHolders <- adapter <- dataSet
    viewHolders: populates the view elements in the UI which is visible to the user
    adapter: binds data to viewHolders
    dataSet: list of items to populate in the viewHolders

- you have to create ViewHolders to keep references in memory.
  To provide a new view, RecyclerView either creates a new ViewHolder object to inflate the layout and hold
  those references, or it recycles one from the existing stack.

-In a RecyclerView, there are features that separate your code
into maintainable components. They enforce memory efficient design patterns.

-The RecyclerView is a ViewGroup that renders any adapter-based view in a similar way. it provides the ability to
implement both horizontal and vertical layouts. Use the RecyclerView widget when you have data collections whose
elements change at runtime based on user action or network events.

-RecyclerView also begins to enforce the ViewHolder pattern too, which was already a recommended practice but
now deeply integrated with this new framework.

-Android used to use ListView & GridView classes to display lists. ListView & GridView only do half the job of
achieving true memory efficiency. Imagine, fb which has complicated custom items(like btn, images, text)
in listView

-ListView & GridView recycle the item layout, but don't keep references to the layout children, forcing you to call
findByView() for every child of your item layout everytime you call getView(). This is processor intensive, especially
for complicated layouts. Furthermore, your listView scrolling becomes jerky or non responsive as it tries to grab
new references.

-If you want to use a RecyclerView, you will need to work with the following:
 RecyclerView.Adapter - To handle the data collection and bind it to the view
 LayoutManager - Helps in positioning the items 1.LinearLayoutManager, 2.GridLayoutManager, 3. StaggeredGridLayoutManager
 ItemAnimator - Helps with animating the items for common operations such as Addition or Removal of item
 */

 /*
   for recyclerView
   tools:listitem / tools:listheader / tools:listfooter
   Intended for: <AdapterView> (and subclasses like <ListView>)

   These attributes specify which layout to show in the layout preview for a list's items, header, and footer.
   Any data fields in the layout are filled with numeric contents such as "Item 1" so that the list items are
   not repetitive.
 */

 /*
 RadioGroup:
 This class is used to create a multiple-exclusion scope for a set of radio buttons. Checking one radio button
 that belongs to a radio group unchecks any previously checked radio button within the same group.
 instead of setOnClickListener(), we use setOnCheckedChangeListener() for radio buttons
 */

 /*In kotlin, Both "val" and "const val" are used for declaring read-only properties of a class. val is
   initialized at the runtime. val deals with the immutable property of a class, that is,
   only read-only variables can be declared using val.
   const val's value is known at compile time of the program.
   const val's cannot be in a class. This means you need to declare it top-level, in an object,or in a companion
   object.*/

  // static: static means that the variable or method marked as such is available at the class level.

  /* Lambda expressions:
     Features: simple & concise, it can be used as a function with no name, it also functions as parameter
     Syntax:  variable_name:(parameters) -> return type
     e.g Lambda as a class constructor parameter
                 class Arithmetic(val sum: (result: Int) -> Boolean){
                   init{sum(5)}
                 }
     * Trigger a Lambda using invoke
                 button.setOnClickListener{
                    sum.invoke(5)
                 }
  */

  /* JetPack is a library. Room database is a part of JetPack. Stores data permanently. Best practice to store data.
     Room allows us to store data in a local database. It is a part of Jetpack suite.
     The Room persistence library provides an abstraction layer over SQLite to allow fluent database access
     while harnessing the full power of SQLite. In particular, Room provides the following benefits:
     -Compile-time verification of SQL queries.
     -Convenience annotations that minimize repetitive and error-prone boilerplate code.
     -Streamlined database migration paths.

      Basically, saving information locally on a device is a very important feature for any Android application
      uses. Most of times users would like to have their data available on the phone easily without needing to
      connect to the internet, and the SQLite database is provided in Android to make saving data locally
      possible.
      But it involves quite a number of boilerplate code lines, so room is a jetpack library was created as a
      wrapper around sQLite.
      That makes it easy to write complex SQL statements to query the data you need without having to
      go through every single column.

      Components of Room: Entity , DAO, Database
  */

// steps: entity -> dao -> database -> app(set the manifest as well)-> adapter

/*
@Volatile in HistoryDatabase:
In kotlin in order to force changes in a variable to be immediately visible to other threads, we can use the
annotation @Volatile. If a variable is not shared between multiple threads, you don't need to use volatile
keyword with that variable.
In other words, When you apply volatile to a field of a class, It instructs the CPU to always read it from the
RAM and not from the CPU cache. It also prevents instructions reordering; it acts as a memory barrier.
*/