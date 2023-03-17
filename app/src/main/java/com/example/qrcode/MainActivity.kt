package com.example.qrcode

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Point
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.WindowManager
import android.widget.EditText
import android.widget.Toast
import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

import com.google.android.material.button.MaterialButton
import java.io.File
import java.io.FileOutputStream

class MainActivity : AppCompatActivity() {

    private lateinit var dataEdt: EditText
    private lateinit var generateQrBtn: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dataEdt = findViewById(R.id.idEdt)
        generateQrBtn = findViewById(R.id.idBtnGenerateQR)

        generateQrBtn.setOnClickListener {
            if (TextUtils.isEmpty(dataEdt.text.toString())) {
                Toast.makeText(this@MainActivity, "Enter Some text to generate QR Code", Toast.LENGTH_SHORT).show()
            } else {
                val manager = getSystemService(Context.WINDOW_SERVICE) as WindowManager
                val display = manager.defaultDisplay
                val point = Point()
                display.getSize(point)
                val width = point.x
                val height = point.y
                var dimen = if (width < height) width else height
                dimen = dimen * 3 / 4

                val qrgEncoder = QRGEncoder(dataEdt.text.toString(), null, QRGContents.Type.TEXT, dimen)
                val bitmap = qrgEncoder.encodeAsBitmap()

                // Save the bitmap to a file
                val file = File(cacheDir, "qr_code.jpg")
                val stream = FileOutputStream(file)
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
                stream.close()
                Log.d("MainActivity", "QR code bitmap saved to file: ${file.absolutePath}")

                // Pass the file path to the second activity
                val intent = Intent(this@MainActivity, SecondActivity::class.java)
                intent.putExtra("qr_bitmap_path", file.absolutePath)
                startActivity(intent)
            }
        }
    }
}
