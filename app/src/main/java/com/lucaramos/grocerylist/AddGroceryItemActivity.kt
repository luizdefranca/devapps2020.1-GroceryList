package com.lucaramos.grocerylist

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.lucaramos.grocerylist.model.GroceryItem
import com.lucaramos.grocerylist.model.GroceryUnit
import kotlinx.android.synthetic.main.activity_add_grocery_item.*

class AddGroceryItemActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    var selectedUnit = "unit"
    val unitList = ArrayList<String>()

    companion object {
        private val SAVED_STATE = "saved_state"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_add_grocery_item)
        setupSpinner()
        restoreState(savedInstanceState)
        cancelButton.setOnClickListener {
            setResult(Activity.RESULT_CANCELED)
            Log.i("setOnclilisteer", "cancelButton pressed")
            finish()
        }

        saveButton.setOnClickListener {
            Log.i("setOnclilisteer", "saveButton pressed")

            if(addDescriptionTextView.text.isNullOrBlank()){
                Toast.makeText(this, "You need to type a description", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val description =  addDescriptionTextView.text?.toString()!!
            val quantity = (addQuantityTextView.text.toString() ?: "0.0").toDouble()
            val observation = addObservationTextView.text?.toString()
            val unit = GroceryUnit.valueOf(selectedUnit) ?: GroceryUnit.unit

            val groceryItem = GroceryItem(description = description,
                quantity = quantity,
                unit = unit,
                observation = observation)
            val data = Intent()
            data.putExtra(MainActivity.GROCERY_ITEM_TEXT, groceryItem)
            setResult(Activity.RESULT_OK, data)
            finish()
        }

    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val description =  addDescriptionTextView.text.toString()?: ""
        val quantity = addQuantityTextView.text.toString().toDouble()?: 0.0
        val observation = addObservationTextView.text.toString()?: ""
        val unit = GroceryUnit.valueOf(selectedUnit)?: GroceryUnit.unit
        val savedGroceryItem = GroceryItem(description,quantity,observation,unit)
        outState.putParcelable(SAVED_STATE, savedGroceryItem)
    }

    private fun setupSpinner() {
        val spinner = addUnitSpinner
        ArrayAdapter.createFromResource(
            this,
            R.array.units_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner.adapter = adapter
        }
        spinner.onItemSelectedListener = this
    }


    override fun onNothingSelected(parent: AdapterView<*>?) {
        Log.i(this.localClassName, "On nothing selected")
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        Log.i(this.localClassName, "On item selected ${position}")

        selectedUnit = resources.getStringArray(R.array.units_array)[position].toString()

        Log.i("onItemSelected", "On item selected ${selectedUnit} foi selecionado")
    }


    private fun restoreState(savedInstanceState: Bundle?) {
        if(savedInstanceState != null){
            val savedGroceryItem = savedInstanceState.getParcelable<GroceryItem>(SAVED_STATE)
            if (savedGroceryItem != null) {
                addDescriptionTextView?.setText(savedGroceryItem.description ?: "")
                addObservationTextView?.setText(savedGroceryItem.observation ?: "")
                addQuantityTextView?.setText(savedGroceryItem.quantity.toString()?: "0.0")
                val selectedPosition = GroceryUnit.values().indexOfFirst {
                    it.name == savedGroceryItem.unit?.name ?: "liter"
                }
                addUnitSpinner?.setSelection(selectedPosition)
            }

        }
    }
}


