package com.example.day14_shoppingapp

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import androidx.core.view.WindowInsetsControllerCompat
import com.example.day14_shoppingapp.databinding.ActivityMainBinding

//https://i.pinimg.com/originals/29/5d/d3/295dd33c4716b80aa201e6debc4d27a3.jpg

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setStatusBarColorAndAppearance("#FFFFFF", true)
        setUpTopAppBar(binding)

        changeDress(binding)

        binding.wishlistBtn.setOnClickListener {
            changeDress(binding)
        }

    }

    fun changeDress(binding: ActivityMainBinding) {
        val randomDress = dressList.random()

        binding.productImg.setImageResource(randomDress)
        val colorString = getMostProminentColor(this@MainActivity, randomDress)
        val color = Color.parseColor(colorString)
        binding.cardViewRating.setCardBackgroundColor(color)
        binding.buyBtn.setBackgroundColor(color)
        binding.priceText.setTextColor(color)
        binding.bgBlob.setColorFilter(color)
    }

    private fun setUpTopAppBar(binding: ActivityMainBinding) {
        val toolbar = binding.topAppBar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.menu)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflate: MenuInflater = menuInflater
        inflate.inflate(R.menu.top_bar_menu, menu)
        return true
    }

    fun Activity.setStatusBarColorAndAppearance(statusBarColor: String, isLight: Boolean ) {
        try {
            window.statusBarColor = (Color.parseColor(statusBarColor))// Or we can use from resource color:  ContextCompat.getColor(mContext, R.color.colorPrimary)
            WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightStatusBars = isLight
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun getMostProminentColor(context: Context, imageResId: Int): String {
        // Load the image from the drawable resource
        val bitmap: Bitmap = BitmapFactory.decodeResource(context.resources, imageResId)

        // Scale down the bitmap for efficiency
        val scaledBitmap: Bitmap = Bitmap.createScaledBitmap(bitmap, 1, 1, false)

        // Extract the pixel color at (0, 0)
        val pixelColor: Int = scaledBitmap.getPixel(0, 0)

        // Convert the color to hexadecimal format
        val hexColor: String = String.format("#%06X", 0xFFFFFF and pixelColor)

        // Return the most prominent color in hexadecimal format
        return hexColor
    }
}