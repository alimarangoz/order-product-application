package com.alimarangoz.orderproduct

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.alimarangoz.orderproduct.config.ApiClient
import com.alimarangoz.orderproduct.models.Cart
import com.alimarangoz.orderproduct.models.CartAdd
import com.alimarangoz.orderproduct.models.ProductCartAdd
import com.alimarangoz.orderproduct.services.CartService
import com.bumptech.glide.Glide
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

class ProductDetail : AppCompatActivity() {

    lateinit var title : TextView
    lateinit var description : TextView
    lateinit var rating : TextView
    lateinit var price : TextView
    lateinit var image : ImageView
    lateinit var addCardBtn : Button
    lateinit var goBtn : Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)

        var extra = intent.extras

        title = findViewById(R.id.product_title)
        description = findViewById(R.id.product_description)
        rating = findViewById(R.id.product_rating)
        price = findViewById(R.id.product_price)
        addCardBtn = findViewById(R.id.addCartBtn)
        goBtn = findViewById(R.id.goBtn)
        image = findViewById(R.id.product_image)

        title.text = intent.getStringExtra("productDetailTitle")
        if (extra != null) {
            price.text = extra.getInt("productDetailPrice").toString() + "₺"
        }
        description.text = intent.getStringExtra("productDetailDescription")
        val images = intent.getStringExtra("productDetailImage")
        if (extra != null) {
            rating.text = extra.getDouble("productDetailRating").toString() + "★"
        }

        Glide.with(this)
            .load(images)
            .into(image);

        addCardBtn.setOnClickListener{
            addToCart();
        }

        goBtn.setOnClickListener{
            var intent = Intent(this@ProductDetail, CartListActivity::class.java)
            startActivity(intent)
        }



    }

    private fun addToCart() {

        val productCartAdd = ProductCartAdd(intent.getLongExtra("id",0),1)
        val arraylist= ArrayList<ProductCartAdd>()
        arraylist.add(productCartAdd)
        val cartAdd = CartAdd(1,arraylist)

        val service = ApiClient.getClient().create(CartService::class.java)


        service.addCart(cartAdd).enqueue(object : Callback<Cart>{
            override fun onResponse(
                call: Call<Cart>,
                response: Response<Cart>
            ) {
                val products = response.body()
                if (products?.products != null){
                    Toast.makeText(this@ProductDetail,"Added to the product",Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<Cart>, t: Throwable) {

                Toast.makeText(this@ProductDetail,"Error Occurred while posting.",Toast.LENGTH_LONG).show()
            }

        })
    }

}
