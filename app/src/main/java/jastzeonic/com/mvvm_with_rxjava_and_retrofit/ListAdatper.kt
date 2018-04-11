package jastzeonic.com.mvvm_with_rxjava_and_retrofit

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

class ListAdatper(private val keys: List<String>, private val items: Map<String, String>) : RecyclerView.Adapter<ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.rootView.context).inflate(R.layout.item, parent, false))
    }

    override fun getItemCount(): Int {
        return keys.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.contentText.text = keys[position] + ":" + items[keys[position]]

    }

}