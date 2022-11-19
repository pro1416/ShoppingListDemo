package com.saaslabs.shoppinglistmvvm.ui.dialogs

import android.content.Context
import android.os.Bundle
import android.view.Window
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialog
import com.saaslabs.shoppinglistmvvm.data.db.entities.ShoppingItem
import com.saaslabs.shoppinglistmvvm.databinding.DialogAddItemBinding
import com.saaslabs.shoppinglistmvvm.ui.interfaces.AddBtnClickedListener

class AddItemDialog(context: Context,var addBtnClickedListener: AddBtnClickedListener) : AppCompatDialog(context) {

    private lateinit var binding : DialogAddItemBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        binding = DialogAddItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAdd.setOnClickListener {
            val itemName = binding.edtItemName.text.toString().trim()
            val itemAmt = binding.edtItemAmount.text.toString().trim()
            if(itemName.isEmpty() || itemAmt.isEmpty()){
                Toast.makeText(context,"Both fields are mandatory!",Toast.LENGTH_SHORT).show()
            }else{
                val item = ShoppingItem(itemName,itemAmt.toInt())
                addBtnClickedListener.btnClicked(item)
                dismiss()
            }
        }

        binding.btnCancel.setOnClickListener {
            cancel()
        }


    }
}