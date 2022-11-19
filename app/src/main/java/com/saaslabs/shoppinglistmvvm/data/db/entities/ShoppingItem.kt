package com.saaslabs.shoppinglistmvvm.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "shopping_items")
data class ShoppingItem(
    @ColumnInfo(name = "item_name")
    var itemName: String,
    @ColumnInfo(name = "item_quantity")
    var itemQuantity: Int
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}