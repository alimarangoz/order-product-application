package com.alimarangoz.orderproduct.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.alimarangoz.orderproduct.R
import com.alimarangoz.orderproduct.models.Cart

class OrderListAdapter(private val orderList: List<Cart>) :
    RecyclerView.Adapter<OrderListAdapter.OrderViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_order, parent, false)
        return OrderViewHolder(view)
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        val order = orderList[position]
        holder.bind(order)
    }

    override fun getItemCount(): Int {
        return orderList.size
    }

    inner class OrderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val orderTitleTextView: TextView = itemView.findViewById(R.id.orderTitleTextView)


        fun bind(cart: Cart) {
            orderTitleTextView.text = cart.products[0].toString()
        }
    }
}