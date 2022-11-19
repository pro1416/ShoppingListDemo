package com.saaslabs.shoppinglistmvvm.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.saaslabs.shoppinglistmvvm.data.db.entities.ShoppingItem
import com.saaslabs.shoppinglistmvvm.databinding.RowShoppingItemBinding
import com.saaslabs.shoppinglistmvvm.viewmodels.ShoppingViewModel

class ShoppingItemAdapter
    (
    var shoppingList: List<ShoppingItem>,
    private val shoppingViewModel: ShoppingViewModel
) : RecyclerView.Adapter<ShoppingItemAdapter.ShoppingItemViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingItemViewHolder {
        val binding =
            RowShoppingItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ShoppingItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ShoppingItemViewHolder, position: Int) {
        val item = shoppingList.get(position)
        holder.itemBinding.txtName.text = item.itemName
        holder.itemBinding.txtAmount.text = item.itemQuantity.toString()
        holder.itemBinding.imgDelete.setOnClickListener {
            shoppingViewModel.deleteItem(item)
        }
        holder.itemBinding.imgPlus.setOnClickListener {
            item.itemQuantity++
            shoppingViewModel.upsertItem(item)
        }
        holder.itemBinding.imgMinus.setOnClickListener {
            if (item.itemQuantity > 0) {
                item.itemQuantity--
                shoppingViewModel.upsertItem(item)
            }
        }
    }

    override fun getItemCount(): Int {
        return shoppingList.size
    }

    inner class ShoppingItemViewHolder(val itemBinding: RowShoppingItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root)

}