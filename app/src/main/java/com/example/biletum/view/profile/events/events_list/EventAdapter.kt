package com.example.biletum.view.profile.events.events_list

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.example.biletum.application.AutoUpdatableAdapter
import com.example.biletum.application.inflate
import com.example.biletum.data.network.model.responses.events.EventItemResponse
import com.example.biletum.helper.DateHelper
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.events_list_event.view.*
import java.util.*
import javax.inject.Inject
import kotlin.properties.Delegates

import java.text.SimpleDateFormat
import com.example.biletum.view.profile.events.events_list.EventAdapter
import android.R
import android.annotation.SuppressLint
import java.text.ParseException


class EventAdapter @Inject constructor(): RecyclerView.Adapter<EventAdapter.MyNotificationViewHolder>(),
    AutoUpdatableAdapter {

    var onItemClickShare: ((EventItemResponse) -> Unit)? = null


    var collection: List<EventItemResponse> by Delegates.observable(emptyList()){ _, oldValue, newValue ->
        autoNotify(old = oldValue, new = newValue){old, new ->
            old.id == new.id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyNotificationViewHolder =
        MyNotificationViewHolder(parent.inflate(com.example.biletum.R.layout.events_list_event))

    override fun getItemCount(): Int = collection.size

    override fun onBindViewHolder(holder: MyNotificationViewHolder, position: Int) {
        holder.bin(item = collection[position])


    }

    fun removeItem(position: Int) {
        notifyItemRemoved(position)
    }

    inner class MyNotificationViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        @SuppressLint("SimpleDateFormat")
        fun bin(item: EventItemResponse){
            itemView.ivent_name.text = item.title

            if (item.city != null && item.country != null){
                itemView.tv_location.text = item.city.name + ", " + item.country.name
            } else{
                itemView.tv_location.text = "without location"
            }

            if(item.image != null) {
                if (item.image!!.isEmpty()) {
                    itemView.iv_image_event.setImageResource(com.example.biletum.R.drawable.pictur)
                } else {
                    Picasso.get().load(item.image).into(itemView.iv_image_event);
                }
            }

            when(item.personal.is_favorite){
                false -> {
                    itemView.iv_is_favorite.setImageResource(com.example.biletum.R.drawable.ic_like)
                }
                true -> {
                    itemView.iv_is_favorite.setImageResource(com.example.biletum.R.drawable.ic_like_not_empty)
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