package com.moutamid.expenseapp

import android.app.Activity
import android.os.Build
import androidx.appcompat.app.AlertDialog
import org.json.JSONException
import org.json.JSONObject
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.MalformedURLException
import java.net.URL

object Constants {
    const val INCOME = "income"
    const val SPENT = "spent"
    const val TOTAL = "total"
    const val HISTORY = "history"
    fun checkApp(activity: Activity) {
        val appName = "expenseManager" //TODO: CHANGE APP NAME
        Thread {
            var google: URL? = null
            try {
                google = URL("https://raw.githubusercontent.com/Moutamid/Moutamid/main/apps.txt")
            } catch (e: MalformedURLException) {
                e.printStackTrace()
            }
            var `in`: BufferedReader? = null
            try {
                `in` = BufferedReader(InputStreamReader(google?.openStream()))
            } catch (e: IOException) {
                e.printStackTrace()
            }
            var input: String? = null
            val stringBuffer = StringBuffer()
            while (true) {
                try {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                        if ((if (`in` != null) `in`.readLine() else null.also {
                                input = it
                            }) == null) break
                    }
                } catch (e: IOException) {
                    e.printStackTrace()
                }
                stringBuffer.append(input)
            }
            try {
                `in`?.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
            val htmlData = stringBuffer.toString()
            try {
                val myAppObject = JSONObject(htmlData).getJSONObject(appName)
                val value = myAppObject.getBoolean("value")
                val msg = myAppObject.getString("msg")
                if (value) {
                    activity.runOnUiThread {
                        AlertDialog.Builder(activity)
                            .setMessage(msg)
                            .setCancelable(false)
                            .show()
                    }
                }
            } catch (e: JSONException) {
                e.printStackTrace()
            }
        }.start()
    }
}