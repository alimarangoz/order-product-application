package com.alimarangoz.orderproduct

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alimarangoz.orderproduct.adapters.OrderListAdapter
import com.alimarangoz.orderproduct.config.ApiClient
import com.alimarangoz.orderproduct.models.CartAdd
import com.alimarangoz.orderproduct.services.OrderService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CartListActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: OrderListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart_list)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)


        val orderService = ApiClient.getClient().create(OrderService::class.java)
        orderService.showCart().enqueue(object : Callback<List<CartAdd>> {
            override fun onResponse(call: Call<List<CartAdd>>, response: Response<List<CartAdd>>) {
                if (response.isSuccessful) {
                    val orderedItems = response.body()
                    if (orderedItems != null) {

                    } else {

                    }
                } else {

                }
            }

            override fun onFailure(call: Call<List<CartAdd>>, t: Throwable) {

            }
        })
    }
}