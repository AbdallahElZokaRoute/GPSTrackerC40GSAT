package com.route.gpstrackerc40gsat.kotlin_tips

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.route.gpstrackerc40gsat.Settings

fun interface OnSettingsItemClickListener {
    fun onSettingsItemClick(item: Settings)
}


val callbackObject: OnSettingsItemClickListener = OnSettingsItemClickListener {
    // navigate or show Toast
    it.title
    it.drawableId
}

// High Level Module ->    Low Level Module
class SettingsAdapter(val settingsList: List<Settings>) :
    Adapter<SettingsAdapter.SettingsViewHolder>() {
    override fun getItemCount(): Int = settingsList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SettingsViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: SettingsViewHolder, position: Int) {
        TODO("Not yet implemented")
    }


    class SettingsViewHolder(val itemSettingsView: View) : ViewHolder(itemSettingsView) {

    }
}

