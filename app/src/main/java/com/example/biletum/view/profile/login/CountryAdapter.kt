package com.example.biletum.view.profile.login

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.example.biletum.application.AutoUpdatableAdapter
import com.example.biletum.application.inflate
import com.example.biletum.data.network.model.models.CountryItem
import kotlinx.android.synthetic.main.country_list_item.view.*
import kotlinx.android.synthetic.main.events_list_event.view.tv_name
import javax.inject.Inject
import kotlin.properties.Delegates


class CountryAdapter @Inject constructor(): RecyclerView.Adapter<CountryAdapter.MyNotificationViewHolder>(), AutoUpdatableAdapter {

    var onItemClick: ((CountryItem) -> Unit)? = null
    var searchableList: MutableList<CountryItem> = arrayListOf()



    var collection: List<CountryItem> by Delegates.observable(emptyList()){ _, oldValue, newValue ->


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyNotificationViewHolder =
        MyNotificationViewHolder(parent.inflate(com.example.biletum.R.layout.country_list_item))

    override fun getItemCount(): Int = collection.size

    override fun onBindViewHolder(holder: MyNotificationViewHolder, position: Int) {
        holder.bin(item = collection[position])


    }

    fun removeItem(position: Int) {
        notifyItemRemoved(position)
    }

    fun updateList(list: List<CountryItem>) {
        collection = list
        notifyDataSetChanged()
    }

    inner class MyNotificationViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        fun bin(item: CountryItem) {
            itemView.tv_name.text = item.name
            itemView.tv_code.text = item.code

            itemView.rl_root.setOnClickListener {
                onItemClick?.invoke(collection[adapterPosition])
            }
        }

    }

}