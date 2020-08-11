package io.github.ovso.rollingbanner

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //
        //
        //https://image.shutterstock.com/z/stock-photo-background-studio-portrait-backdrops-1189873804.jpg
        val items =
            listOf(
                R.drawable.bg0,
                R.drawable.bg1,
                R.drawable.bg2
            )
/*
        val items =
            listOf(
                "https://image.shutterstock.com/z/stock-vector-abstract-lines-and-dots-connect-background-technology-connection-digital-data-and-big-data-concept-1492332182.jpg",
                "https://image.shutterstock.com/z/stock-photo-texture-dark-concrete-floor-with-mist-or-fog-513032461.jpg",
                "https://image.shutterstock.com/z/stock-vector-abstract-lines-and-dots-connect-background-technology-connection-digital-data-and-big-data-concept-1492332182.jpg"
            )
*/

        val adapter = BannerAdapter()
        vp2Main.adapter = adapter
        adapter.submitList(items)
//        Glide.with(ivMain).load(
//            "https://image.shutterstock.com/z/stock-vector-abstract-lines-and-dots-connect-background-technology-connection-digital-data-and-big-data-concept-1492332182.jpg"
//        ).into(ivMain)
    }
}