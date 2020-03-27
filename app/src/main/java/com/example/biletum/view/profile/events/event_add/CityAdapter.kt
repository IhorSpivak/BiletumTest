package com.example.biletum.view.profile.events.event_add



import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.example.biletum.application.AutoUpdatableAdapter
import com.example.biletum.application.inflate
import com.example.biletum.data.network.model.models.CityItem
import kotlinx.android.synthetic.main.country_list_item.view.*
import javax.inject.Inject
import kotlin.properties.Delegates


class CityAdapter @Inject constructor(): RecyclerView.Adapter<CityAdapter.MyNotificationViewHolder>(), AutoUpdatableAdapter {

    var onItemClick: ((CityItem) -> Unit)? = null
    var searchableList: MutableList<CityItem> = arrayListOf()



    var collection: List<CityItem> by Delegates.observable(emptyList()){ _, oldValue, newValue ->


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

    fun updateList(list: List<CityItem>) {
        collection = list
        notifyDataSetChanged()
    }

    inner class MyNotificationViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        fun bin(item: CityItem) {
            itemView.tv_location.text = item.name

            itemView.rl_root.setOnClickListener {
                onItemClick?.invoke(collection[adapterPosition])
            }
        }

    }

}