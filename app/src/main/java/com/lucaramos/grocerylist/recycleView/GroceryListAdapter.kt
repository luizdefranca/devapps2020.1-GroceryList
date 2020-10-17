package com.lucaramos.grocerylist.recycleView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lucaramos.grocerylist.R
import com.lucaramos.grocerylist.model.GroceryItem
import kotlinx.android.synthetic.main.grocery_list_view_holder.view.*

class GroceryListAdapter(groceryList : MutableList<GroceryItem> = ArrayList()) : RecyclerView.Adapter<GroceryListAdapter.GroceryListViewHolder>(), View.OnClickListener {

    private var groceryList : MutableList<GroceryItem> = groceryList
            set(value) {
                field = value
                notifyDataSetChanged()
            }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroceryListViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.grocery_list_view_holder, parent, false)
        return GroceryListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return groceryList.size
    }

    override fun onBindViewHolder(holder: GroceryListViewHolder, position: Int) {
        val item = groceryList[position]
        holder.descriptionTextView.text = item.description
        holder.quantityTextView.text = item.quantity.toString()
        holder.unitTextView.text = item.unit.toString()
    }

    //Created Functions
    fun addGroceryItem(item : GroceryItem){
        groceryList.add(item)
        notifyDataSetChanged()
    }


    inner class GroceryListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var descriptionTextView : TextView = itemView.descriptionTextView
        var quantityTextView : TextView = itemView.quantityTextView
        var unitTextView : TextView = itemView.unitTextView
    }

    override fun onClick(v: View?) {
        TODO("Not yet implemented")
    }

}
