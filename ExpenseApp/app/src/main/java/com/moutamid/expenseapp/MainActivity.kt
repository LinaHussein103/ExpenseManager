package com.moutamid.expenseapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.fxn.stash.Stash
import com.moutamid.expenseapp.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {
    var binding: ActivityMainBinding? = null
    var balance = 0.0
    var income = 0.0
    var spent = 0.0
    var list: ArrayList<Model?>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        Constants.checkApp(this)

        balance = Stash.getString(Constants.TOTAL, "0").toDouble()
        income = Stash.getString(Constants.INCOME, "0").toDouble()
        spent = Stash.getString(Constants.SPENT, "0").toDouble()
        list = ArrayList()
        list = Stash.getArrayList(Constants.HISTORY, Model::class.java)
        Collections.reverse(list)
        binding!!.recyler.layoutManager = LinearLayoutManager(this)
        binding!!.recyler.setHasFixedSize(false)
        val adapter = HistorySpent(this, list)
        binding!!.recyler.adapter = adapter
        binding!!.balance.text = "$" + String.format("%,.2f", balance)
        binding!!.income.text = "$" + String.format("%,.2f", income)
        binding!!.spent.text = "$" + String.format("%,.2f", spent)
        binding!!.addSpent.setOnClickListener { v: View? ->
            startActivity(Intent(this, SubActivity::class.java))
            finish()
        }
        binding!!.addIncome.setOnClickListener { v: View? ->
            startActivity(Intent(this, AddActivity::class.java))
            finish()
        }
    }
}