package com.saaslabs.shoppinglistmvvm.viewmodels

import androidx.lifecycle.ViewModel
import com.saaslabs.shoppinglistmvvm.data.db.entities.ShoppingItem
import com.saaslabs.shoppinglistmvvm.data.repositories.ShoppingRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ShoppingViewModel(private val repository: ShoppingRepository): ViewModel(){

    fun upsertItem(item:ShoppingItem) = GlobalScope.launch { repository.upsert(item) }

    fun deleteItem(item :ShoppingItem) = GlobalScope.launch { repository.delete(item) }

    fun getAllItem() = repository.getAllItems()
}