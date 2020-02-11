package com.example.biletum.application

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

interface AutoUpdatableAdapter {

    fun <R> RecyclerView.Adapter<*>.autoNotify(old: List<R>, new: List<R>, compare: (R, R) -> Boolean){
        val diff = DiffUtil.calculateDiff(object : DiffUtil.Callback(){
            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
                compare(old[oldItemPosition], new[newItemPosition])

            override fun getOldListSize(): Int = old.size

            override fun getNewListSize(): Int = new.size

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
                old[oldItemPosition] == new[newItemPosition]

        })
        diff.dispatchUpdatesTo(this)
    }
}