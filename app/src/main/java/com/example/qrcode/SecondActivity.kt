package com.example.qrcode

import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {

    private lateinit var qrCodeIV: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second_actvity)

        qrCodeIV = findViewById(R.id.idIVQrcode)

        val bitmapPath = intent.getStringExtra("qr_bitmap_path")
        if (bitmapPath != null) {
            val bitmap = BitmapFactory.decodeFile(bitmapPath)
            qrCodeIV.setImageBitmap(bitmap)
        }

    }
}
