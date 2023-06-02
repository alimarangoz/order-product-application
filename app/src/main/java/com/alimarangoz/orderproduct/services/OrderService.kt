package com.alimarangoz.orderproduct.services

import com.alimarangoz.orderproduct.models.CartAdd
import retrofit2.Call
import retrofit2.http.GET

interface OrderService {
    @GET("carts")
    fun showCart() : Call<List<CartAdd>>
}