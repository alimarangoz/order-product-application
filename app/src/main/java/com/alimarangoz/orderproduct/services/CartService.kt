package com.alimarangoz.orderproduct.services

import com.alimarangoz.orderproduct.models.Cart
import com.alimarangoz.orderproduct.models.CartAdd
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

interface CartService {
    @POST("carts/add")
    fun addCart(@Body cartData: CartAdd) : Call<Cart>

}