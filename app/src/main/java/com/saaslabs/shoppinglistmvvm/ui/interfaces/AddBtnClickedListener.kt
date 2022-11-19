package com.saaslabs.shoppinglistmvvm.ui.interfaces

import com.saaslabs.shoppinglistmvvm.data.db.entities.ShoppingItem

interface AddBtnClickedListener {
    fun btnClicked(item: ShoppingItem)
}