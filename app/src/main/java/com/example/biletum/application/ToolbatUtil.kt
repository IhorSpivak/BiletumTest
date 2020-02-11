package com.example.biletum.application

import android.view.View
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavDestination


class ToolbarUtil(private val supportActionBar: ActionBar?, private val toolbar: Toolbar) {

    init {
        setupToolbar()
    }

    private fun setupToolbar(){

    }

    fun onDestinationChanged(destination: NavDestination){
        toolbar.title = ""
//        toolbarTitle.text = destination.label
        toolbar.visibility = View.VISIBLE


//        if(destination.id !in topLevelFragments){
//
//        }else{
//            supportActionBar?.setHomeAsUpIndicator(0)
//        }


    }

    companion object{
//        val topLevelFragments = setOf(R.id.wallet_dest, R.id.exchanged_dest, R.id.contracts_dest, R.id.monitor_dest)
    }
}