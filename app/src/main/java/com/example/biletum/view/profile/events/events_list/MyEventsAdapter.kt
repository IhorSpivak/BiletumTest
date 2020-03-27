package com.example.biletum.view.profile.events.events_list

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.biletum.R
import com.example.biletum.application.AutoUpdatableAdapter
import com.example.biletum.application.inflate
import com.example.biletum.data.network.model.responses.events.EventItemResponse
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.big_event_item.view.*
import kotlinx.android.synthetic.main.big_event_item.view.rl_bg_share
import kotlinx.android.synthetic.main.big_event_item.view.tv_date
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import kotlin.properties.Delegates

class MyEventsAdapter @Inject constructor(): RecyclerView.Adapter<MyEventsAdapter.MyNotificationViewHolder>(),
    AutoUpdatableAdapter {

    var onItemClickShare: ((EventItemResponse) -> Unit)? = null


    var collection: List<EventItemResponse> by Delegates.observable(emptyList()){ _, oldValue, newValue ->
        autoNotify(old = oldValue, new = newValue){old, new ->
            old.id == new.id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyNotificationViewHolder =
        MyNotificationViewHolder(parent.inflate(R.layout.big_event_item))

    override fun getItemCount(): Int = collection.size

    override fun onBindViewHolder(holder: MyNotificationViewHolder, position: Int) {
        holder.bin(item = collection[position])


    }

    fun removeItem(position: Int) {
        notifyItemRemoved(position)
    }

    inner class MyNotificationViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        fun bin(item: EventItemResponse){


            itemView.ivent_name.text = item.title
            itemView.tickets.text = item.personal.tickets.toString()
            itemView.tv_contacts.text = item.personal.contacts.toString()
            itemView.tv_contacts.text = item.personal.contacts.toString()
            itemView.tv_notes.text = item.personal.notes.toString()


            if(item.image != null) {
                if (item.image!!.isEmpty()) {
                    itemView.iv_image_event.setImageResource(com.example.biletum.R.drawable.pictur)
                } else {
                    Picasso.get().load(item.image).into(itemView.iv_image_event);
                }
            }


            val inputFormat = SimpleDateFormat("yyyy-MM-dd")
            val outputFormat = SimpleDateFormat("dd MMM yyyy")
            val startDateStr = "${item.date_start?.replace(oldChar = 'T', newChar = ' ', ignoreCase = false)
                ?.replace(oldChar = 'Z', newChar = ' ', ignoreCase = false)}".substring(0,10)
            var date: Date? = null
            var startDateStrNewFormat:String = ""
            try {
                date = inputFormat.parse(startDateStr)
                startDateStrNewFormat = outputFormat.format(date)
            } catch (e: ParseException) {
                e.printStackTrace()
            }

            val endDateStr =  "${item.date_end?.replace(oldChar = 'T', newChar = ' ', ignoreCase = false)
                ?.replace(oldChar = 'Z', newChar = ' ', ignoreCase = false)}".substring(0,10)
            var endDate: Date? = null
            var endDateStrNewFormat:String = ""
            try {
                endDate = inputFormat.parse(endDateStr)
                endDateStrNewFormat = outputFormat.format(endDate)
            } catch (e: ParseException) {
                e.printStackTrace()
            }


            itemView.tv_date.text = try {startDateStrNewFormat.substring(0,7) + " - " + endDateStrNewFormat
            } catch (e: Exception){""}
            itemView.rl_bg_share.setOnClickListener {
                onItemClickShare?.invoke(collection[adapterPosition])
            }

        }



    }

}