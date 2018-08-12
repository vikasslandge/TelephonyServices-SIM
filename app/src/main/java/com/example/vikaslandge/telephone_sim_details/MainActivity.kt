package com.example.vikaslandge.telephone_sim_details

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.telephony.TelephonyManager
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var tManager = getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager

        var list = mutableListOf<String>()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            list.add(tManager.imei)
        }
        list.add(tManager.simOperatorName)
        list.add(tManager.simCountryIso)
        list.add(tManager.simSerialNumber)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            list.add(tManager.dataNetworkType.toString())
        }
        var cManager=getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if(cManager.activeNetworkInfo!=null)
            list.add(cManager.activeNetworkInfo.isConnected.toString())
        else
            list.add("false")
        var myadapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_single_choice,list)
        lview.adapter=myadapter
    }
}
