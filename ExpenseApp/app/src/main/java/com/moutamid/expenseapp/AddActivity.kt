package com.moutamid.expenseapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.fxn.stash.Stash
import com.moutamid.expenseapp.databinding.ActivityAddBinding

class AddActivity : AppCompatActivity() {
    var binding: ActivityAddBinding? = null
    var list: ArrayList<Model>? = null
    var income = 0.0
    var balance = 0.0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        binding!!.back.setOnClickListener { v: View? -> onBackPressed() }
        list = ArrayList()
        list = Stash.getArrayList(Constants.HISTORY, Model::class.java)
        income = Stash.getString(Constants.INCOME, "0").toDouble()
        balance = Stash.getString(Constants.TOTAL, "0").toDouble()
        Log.d("INCOME", "income $income")
        binding!!.addIncome.setOnClickListener { v: View? ->
            if (binding!!.amount.editText!!
                    .text.toString().isEmpty()
            ) {
                Toast.makeText(this, "Add income", Toast.LENGTH_SHORT).show()
            } else {
                var amount = binding!!.amount.editText!!.text.toString().toDouble()
                list?.add(Model(binding!!.desc.editText!!.text.toString(), amount, false))//todo:added
                balance = balance + amount
                amount = amount + income
                Stash.put(Constants.HISTORY, list)
                Stash.put(Constants.INCOME, "" + amount)
                Stash.put(Constants.TOTAL, "" + balance)
                Toast.makeText(this, "Income Added", Toast.LENGTH_SHORT).show()
                onBackPressed()
            }
        }
    }

    override fun onBackPressed() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}