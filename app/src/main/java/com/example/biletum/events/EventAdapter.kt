package com.example.biletum.events

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.biletum.R
import com.example.biletum.application.AutoUpdatableAdapter
import com.example.biletum.application.inflate
import com.example.biletum.data.network.model.responses.events.EventItemResponse
import java.text.DecimalFormat
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
//            itemView.tv_name.text = item.name
//            itemView.tv_currency.text = item.currency
//            if(item.balance != null) {
//                val dollarBalance = item.balance!! * item.dollarRates!!
//                DecimalFormat("##.##").format(dollarBalance).toString()
//                itemView.tv__fiat_value.text =
//                    "USD " + DecimalFormat("##.##").format(dollarBalance).toString()
//            }
//            itemView.tv_currency_value.text = item.balance.toString()
//
//
//            itemView.ic_qr.setOnClickListener {
//                onItemClickQR?.invoke(collection[adapterPosition])
//            }
//
//            itemView.ic_history.setOnClickListener {
//                onItemClickHistory?.invoke(collection[adapterPosition])
//            }
//
//            itemView.rl_more.setOnClickListener {
//                onItemClickMore?.invoke(collection[adapterPosition])
//            }
//
//            itemView.root.setOnClickListener {
//                onItemClickRoot?.invoke(collection[adapterPosition])
//            }

        }



    }

}