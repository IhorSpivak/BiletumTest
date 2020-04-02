package com.example.biletum.view.profile.events.event_add.adapters

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.biletum.application.AutoUpdatableAdapter
import com.example.biletum.application.inflate
import com.example.biletum.data.network.model.models.ItemCountry
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.big_event_item.view.iv_image_event
import kotlinx.android.synthetic.main.country_list_item.view.*
import kotlinx.android.synthetic.main.image_item.view.*
import javax.inject.Inject
import kotlin.properties.Delegates

class ImageAdapter @Inject constructor(): RecyclerView.Adapter<ImageAdapter.MyNotificationViewHolder>(),
    AutoUpdatableAdapter {

    var onItemClick: ((String) -> Unit)? = null
    var searchableList: MutableList<String> = arrayListOf()



    var collection: List<String> by Delegates.observable(emptyList()){ _, oldValue, newValue ->


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyNotificationViewHolder =
        MyNotificationViewHolder(parent.inflate(com.example.biletum.R.layout.image_item))

    override fun getItemCount(): Int = collection.size

    override fun onBindViewHolder(holder: MyNotificationViewHolder, position: Int) {
        holder.bin(item = collection[position])


    }

    fun removeItem(position: Int) {
        notifyItemRemoved(position)
    }

    fun updateList(list: List<String>) {
        collection = list
        notifyDataSetChanged()
    }

    inner class MyNotificationViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        fun bin(item: String) {
            Picasso.get().load(item).into(itemView.iv_image_event)

            itemView.root.setOnClickListener {
                onItemClick?.invoke(collection[adapterPosition])
            }
            }
        }


}