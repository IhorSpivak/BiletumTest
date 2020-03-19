package com.example.biletum.view.profile.events.event_add

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.biletum.application.AutoUpdatableAdapter
import com.example.biletum.application.inflate
import com.example.biletum.data.network.model.models.CategoryItem
import com.example.biletum.data.network.model.models.TypeItem
import kotlinx.android.synthetic.main.category_item.view.*
import kotlinx.android.synthetic.main.type_item.view.*
import kotlinx.android.synthetic.main.type_item.view.rl_root
import kotlinx.android.synthetic.main.type_item.view.tv_name
import javax.inject.Inject
import kotlin.properties.Delegates

class CategoryEventAdapter @Inject constructor(): RecyclerView.Adapter<CategoryEventAdapter.MyViewHolder>(),
    AutoUpdatableAdapter {

    var onItemClick: ((CategoryItem) -> Unit)? = null
    var typesList: MutableList<CategoryItem> = arrayListOf()


    var collection: List<CategoryItem> by Delegates.observable(emptyList()) { _, oldValue, newValue ->


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder =
        MyViewHolder(parent.inflate(com.example.biletum.R.layout.category_item))

    override fun getItemCount(): Int = collection.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bin(item = collection[position])


    }

    fun removeItem(position: Int) {
        notifyItemRemoved(position)
    }

    fun updateList(list: List<CategoryItem>) {
        collection = list
        notifyDataSetChanged()
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bin(item: CategoryItem) {

            itemView.cb.isChecked = item.isCheked
            itemView.tv_name.text = item.name

            itemView.rl_root.setOnClickListener {
                item.isCheked = !item.isCheked
                itemView.cb.isChecked = item.isCheked

            }
        }

    }
}
