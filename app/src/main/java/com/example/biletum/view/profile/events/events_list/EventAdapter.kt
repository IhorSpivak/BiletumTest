package com.example.biletum.view.profile.events.events_list

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.biletum.R
import com.example.biletum.application.AutoUpdatableAdapter
import com.example.biletum.application.inflate
import com.example.biletum.data.network.model.responses.events.EventItemResponse
import kotlinx.android.synthetic.main.events_list_event.view.*
import javax.inject.Inject
import kotlin.properties.Delegates

class EventAdapter @Inject constructor(): RecyclerView.Adapter<EventAdapter.MyNotificationViewHolder>(),
    AutoUpdatableAdapter {

    var onItemClickQR: ((EventItemResponse) -> Unit)? = null


    var collection: List<EventItemResponse> by Delegates.observable(emptyList()){ _, oldValue, newValue ->
        autoNotify(old = oldValue, new = newValue){old, new ->
            old.id == new.id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyNotificationViewHolder =
        MyNotificationViewHolder(parent.inflate(R.layout.events_list_event))

    override fun getItemCount(): Int = collection.size

    override fun onBindViewHolder(holder: MyNotificationViewHolder, position: Int) {
        holder.bin(item = collection[position])


    }

    fun removeItem(position: Int) {
        notifyItemRemoved(position)
    }

    inner class MyNotificationViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        fun bin(item: EventItemResponse){
            itemView.tv_name.text = item.title
            itemView.tv_currency.text = try {
                    "${item.date_start?.replace(oldChar = 'T', newChar = ' ', ignoreCase = false)
                        ?.replace(oldChar = 'Z', newChar = ' ', ignoreCase = false)}"
                } catch (e: Exception){""}

            itemView.tv_date_end_value.text = try {
                "${item.date_end?.replace(oldChar = 'T', newChar = ' ', ignoreCase = false)
                    ?.replace(oldChar = 'Z', newChar = ' ', ignoreCase = false)}"
            } catch (e: Exception){""}

//
//            itemView.root.setOnClickListener {
//                onItemClickRoot?.invoke(collection[adapterPosition])
//            }

        }



    }

}