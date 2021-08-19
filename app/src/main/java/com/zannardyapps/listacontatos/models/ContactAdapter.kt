package com.zannardyapps.listacontatos.models

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zannardyapps.listacontatos.ClickItemContactListener
import com.zannardyapps.listacontatos.R

class
ContactAdapter(private var listener: ClickItemContactListener)
    : RecyclerView.Adapter<ContactAdapter.ContactAdapterViewHolder>() {

    private var listObjects: MutableList<DataContact> = mutableListOf()
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactAdapterViewHolder {
        val view =
            LayoutInflater.
            from(parent.context).
            inflate(R.layout.contact_item, parent, false)

        return ContactAdapterViewHolder(view, listObjects, listener)

    }

    override fun onBindViewHolder(holder: ContactAdapterViewHolder, position: Int) {
        holder.bind(listObjects[position])
    }

    override fun getItemCount(): Int {
        return listObjects.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(newList:List<DataContact>){
        this.listObjects.clear()
        this.listObjects.addAll(newList)
        notifyDataSetChanged()
    }


    inner class
    ContactAdapterViewHolder(itemView: View, var list: List<DataContact>, var listener: ClickItemContactListener)
        :RecyclerView.ViewHolder(itemView){

        private val name: TextView = itemView.findViewById(R.id.textViewName)
        private val number: TextView = itemView.findViewById(R.id.textViewNumber)
        private val image: ImageView = itemView.findViewById(R.id.contactPhotoXml)

        init {
            itemView.setOnClickListener {
                listener.itemClickedAction(list[adapterPosition])
            }
        }

        fun bind(contact: DataContact){
           name.text = contact.contactName
           number.text = contact.contactNumber}

    }
}