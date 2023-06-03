package com.moutamid.expenseapp

import android.app.Application
import com.fxn.stash.Stash

class MyApplicationn : Application() {
    override fun onCreate() {
        super.onCreate()
        Stash.init(this)
    }
}