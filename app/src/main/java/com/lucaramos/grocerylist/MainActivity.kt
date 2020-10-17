package com.lucaramos.grocerylist

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lucaramos.grocerylist.model.GroceryItem
import com.lucaramos.grocerylist.model.GroceryUnit
import com.lucaramos.grocerylist.recycleView.GroceryListAdapter
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        private const val ADD_GROCERY_ITEM_ACTIVITY_REQUEST_CODE = 0
        val GROCERY_ITEM_TEXT= "groceryItem"
    }
    lateinit var recicleView : RecyclerView
    private var groceryList : MutableList<GroceryItem> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        groceryRecycleView.layoutManager = LinearLayoutManager(this)
        groceryRecycleView.adapter =
            GroceryListAdapter(groceryList)

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            val intent = Intent(this, AddGroceryItemActivity::class.java)
            startActivityForResult(intent, ADD_GROCERY_ITEM_ACTIVITY_REQUEST_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == ADD_GROCERY_ITEM_ACTIVITY_REQUEST_CODE){
            if(resultCode == Activity.RESULT_OK){
                val groceryItem = data?.getParcelableExtra<GroceryItem>(GROCERY_ITEM_TEXT)
                groceryItem?.let {
                    (groceryRecycleView.adapter as GroceryListAdapter).addGroceryItem(groceryItem)
                    Log.i("testando", "unit ${groceryItem?.unit.toString()}")
                }
            }
        }
    }

}