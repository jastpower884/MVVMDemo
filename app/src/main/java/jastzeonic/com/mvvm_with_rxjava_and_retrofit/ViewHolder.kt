package jastzeonic.com.mvvm_with_rxjava_and_retrofit

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView

class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


    var contentText: TextView = itemView.findViewById(R.id.text_view)


}