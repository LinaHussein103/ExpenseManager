package com.moutamid.expenseapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.fxn.stash.Stash
import com.moutamid.expenseapp.databinding.ActivitySubBinding


class SubActivity : AppCompatActivity() {
    var binding: ActivitySubBinding? = null
    var list: ArrayList<Model>? = null
    var spent = 0.0
    var balance = 0.0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySubBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        binding!!.back.setOnClickListener { v: View? -> onBackPressed() }
        list = ArrayList()
        list = Stash.getArrayList(Constants.HISTORY, Model::class.java)
        spent = Stash.getString(Constants.SPENT, "0").toDouble()
        balance = Stash.getString(Constants.TOTAL, "0").toDouble()
        Log.d("INCOME", "income $spent")
        binding!!.addSpent.setOnClickListener { v: View? ->
            if (binding!!.amount.editText!!
                    .text.toString().isEmpty()
            ) {
                Toast.makeText(this, "Add Amount", Toast.LENGTH_SHORT).show()
            } else {
                var amount = binding!!.amount.editText!!.text.toString().toDouble()
                list?.add(Model(binding!!.desc.editText!!.text.toString(), amount, true))
                balance = balance - amount
                amount = amount + spent
                Stash.put(Constants.HISTORY, list)
                Stash.put(Constants.SPENT, "" + amount)
                Stash.put(Constants.TOTAL, "" + balance)
                Toast.makeText(this, "Spent Added", Toast.LENGTH_SHORT).show()
                onBackPressed()
            }
        }
    }

    override fun onBackPressed() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}