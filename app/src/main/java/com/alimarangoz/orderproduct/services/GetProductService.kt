package com.alimarangoz.orderproduct.services

import com.alimarangoz.orderproduct.models.ProductList
import retrofit2.Call
import retrofit2.http.GET

interface GetProductService {

    @GET("/products")
    fun getProduct(): Call<ProductList>

}