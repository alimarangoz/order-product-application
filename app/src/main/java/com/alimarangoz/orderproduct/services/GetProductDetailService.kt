package com.alimarangoz.orderproduct.services

import com.alimarangoz.orderproduct.models.Product
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GetProductDetailService {

    @GET("product/{productId}")
    fun getProductDetail(@Path("productId") productId: Int): Call<Product>
}