package com.saaslabs.shoppinglistmvvm.ui.activities

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.saaslabs.shoppinglistmvvm.data.db.ShoppingDatabase
import com.saaslabs.shoppinglistmvvm.data.db.entities.ShoppingItem
import com.saaslabs.shoppinglistmvvm.data.repositories.ShoppingRepository
import com.saaslabs.shoppinglistmvvm.databinding.ActivityShoppingBinding
import com.saaslabs.shoppinglistmvvm.ui.dialogs.AddItemDialog
import com.saaslabs.shoppinglistmvvm.ui.adapters.ShoppingItemAdapter
import com.saaslabs.shoppinglistmvvm.ui.interfaces.AddBtnClickedListener
import com.saaslabs.shoppinglistmvvm.viewmodels.ShoppingViewModel
import com.saaslabs.shoppinglistmvvm.viewmodels.ShoppingViewModelFactory

class ShoppingActivity : AppCompatActivity()  {

    private lateinit var binding: ActivityShoppingBinding

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityShoppingBinding.inflate(layoutInflater)
        setContentView(binding.root)



        val database = ShoppingDatabase(this)
        val repository = ShoppingRepository(database)
        val factory = ShoppingViewModelFactory(repository)

        val shoppingViewModel = ViewModelProvider(this,factory).get(ShoppingViewModel::class.java)

        val adapter = ShoppingItemAdapter(listOf(),shoppingViewModel)

        binding.rvShoppingItems.layoutManager = LinearLayoutManager(this)
        binding.rvShoppingItems.adapter = adapter

        shoppingViewModel.getAllItem().observe(this, Observer {
            adapter.shoppingList = it
            adapter.notifyDataSetChanged()
        })

        binding.fabAddItem.setOnClickListener {
            val dialog = AddItemDialog(this,
            object : AddBtnClickedListener {
                override fun btnClicked(item: ShoppingItem) {
                    shoppingViewModel.upsertItem(item)
                }

            }).show()
        }


    }
}