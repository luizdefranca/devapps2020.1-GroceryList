package com.lucaramos.grocerylist.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GroceryItem(var description: String,
                        var quantity: Double?,
                        var observation: String?,
                        var unit: GroceryUnit? ): Parcelable {
}