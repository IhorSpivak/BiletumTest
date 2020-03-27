package com.example.biletum.view.profile.events.event_add

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.biletum.application.AutoUpdatableAdapter
import com.example.biletum.application.inflate
import com.example.biletum.data.network.model.models.TypeItem
import kotlinx.android.synthetic.main.type_item.view.*
import javax.inject.Inject
import kotlin.properties.Delegates


class TypesEventAdapter @Inject constructor(): RecyclerView.Adapter<TypesEventAdapter.MyViewHolder>(),
    AutoUpdatableAdapter {

    var onItemClick: ((TypeItem) -> Unit)? = null
    var typesList: MutableList<TypeItem> = arrayListOf()


    var collection: List<TypeItem> by Delegates.observable(emptyList()) { _, oldValue, newValue ->


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder =
        MyViewHolder(parent.inflate(com.example.biletum.R.layout.type_item))

    override fun getItemCount(): Int = collection.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bin(item = collection[position])


    }

    fun removeItem(position: Int) {
        notifyItemRemoved(position)
    }

    fun updateList(list: List<TypeItem>) {
        collection = list
        notifyDataSetChanged()
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bin(item: TypeItem) {
            itemView.tv_location.text = item.name

            itemView.rl_root.setOnClickListener {
                onItemClick?.invoke(collection[adapterPosition])
            }
        }

    }
}

