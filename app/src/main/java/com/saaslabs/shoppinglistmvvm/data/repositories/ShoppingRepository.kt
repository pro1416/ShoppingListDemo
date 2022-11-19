package com.saaslabs.shoppinglistmvvm.data.repositories

import com.saaslabs.shoppinglistmvvm.data.db.ShoppingDatabase
import com.saaslabs.shoppinglistmvvm.data.db.entities.ShoppingItem

class ShoppingRepository (private val db : ShoppingDatabase){

    suspend fun upsert(item : ShoppingItem) = db.getShoppingDao().upsert(item)

    suspend fun  delete(item : ShoppingItem) = db.getShoppingDao().delete(item)

    fun getAllItems() = db.getShoppingDao().getAllItems()
}