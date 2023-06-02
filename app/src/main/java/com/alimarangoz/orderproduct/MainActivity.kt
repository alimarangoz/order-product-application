package com.alimarangoz.orderproduct

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.widget.AdapterView
import android.widget.ListView
import android.widget.Toast
import com.alimarangoz.orderproduct.adapters.CustomAdapter
import com.alimarangoz.orderproduct.config.ApiClient
import com.alimarangoz.orderproduct.models.Product
import com.alimarangoz.orderproduct.models.ProductList
import com.alimarangoz.orderproduct.services.GetProductDetailService
import com.alimarangoz.orderproduct.services.GetProductService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create
import java.io.Serializable

class MainActivity : AppCompatActivity() {

    lateinit var listView : ListView
    lateinit var productListAdapter : CustomAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listView = findViewById(R.id.listView)

        val productService = ApiClient.getClient().create(GetProductService::class.java)
        val productDetailService = ApiClient.getClient().create(GetProductDetailService::class.java)

        productService.getProduct().enqueue(object : Callback<ProductList> {
            override fun onResponse(call: Call<ProductList>, response: Response<ProductList>) {
                val productList = response.body()
                if (productList != null) {
                    productListAdapter = CustomAdapter(this@MainActivity, productList)
                    listView.adapter = productListAdapter
                }

            }

            override fun onFailure(call: Call<ProductList>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Error occurred when loading products, try again!", Toast.LENGTH_LONG).show()
            }

        })

        listView.onItemClickListener = AdapterView.OnItemClickListener { adapterView, view, i, l ->
            val selectedProduct = productListAdapter.getItem(i) as Product
            val productId = selectedProduct.id

            productDetailService.getProductDetail(productId).enqueue(object : Callback<Product>{
                override fun onResponse(call: Call<Product>, response: Response<Product>) {
                    val productDetail = response.body()
                    if (productDetail != null){
                        var intent = Intent(this@MainActivity, ProductDetail::class.java);
                        intent.putExtra("productDetailTitle",productDetail.title)
                        intent.putExtra("productDetailPrice",productDetail.price)
                        intent.putExtra("productDetailImage",productDetail.images[0])
                        intent.putExtra("productDetailDescription",productDetail.description)
                        intent.putExtra("productDetailRating",productDetail.rating)
                        startActivity(intent)
                    }
                }

                override fun onFailure(call: Call<Product>, t: Throwable) {
                    Toast.makeText(this@MainActivity, "Error occurred when loading products detail, try again!", Toast.LENGTH_LONG).show()
                }

            })
        }
    }

}
