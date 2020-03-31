package com.example.biletum.view.profile.events.event_add.adapters

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.biletum.application.AutoUpdatableAdapter
import com.example.biletum.application.inflate
import com.example.biletum.data.network.model.models.EventType
import kotlinx.android.synthetic.main.category_item.view.*
import kotlinx.android.synthetic.main.type_item.view.rl_root
import kotlinx.android.synthetic.main.type_item.view.tv_location
import javax.inject.Inject
import kotlin.properties.Delegates


class TypesEventAdapter @Inject constructor(): RecyclerView.Adapter<TypesEventAdapter.MyViewHolder>(),
    AutoUpdatableAdapter {

    var onItemClick: ((EventType) -> Unit)? = null
    var typesList: MutableList<EventType> = arrayListOf()


    var collection: List<EventType> by Delegates.observable(emptyList()) { _, oldValue, newValue ->


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder =
        MyViewHolder(parent.inflate(com.example.biletum.R.layout.grid_layout))

    override fun getItemCount(): Int = collection.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bin(item = collection[position])


    }

    fun removeItem(position: Int) {
        notifyItemRemoved(position)
    }

    fun updateList(list: List<EventType>) {
        collection = list
        notifyDataSetChanged()
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bin(item: EventType) {
            itemView.cb.isChecked = item.isCheked
            itemView.tv_location.text = item.name

            itemView.rl_root.setOnClickListener {
                item.isCheked = !item.isCheked
                itemView.cb.isChecked = item.isCheked

            }
        }

    }
}

