package com.example.egci428_camera18

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import android.Manifest
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {

    lateinit var photoBtn: Button
    lateinit var imageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        photoBtn = findViewById(R.id.photoBtn)
        imageView = findViewById(R.id.imageView)
    }

    fun takePhoto(view: View){
        requestCameraPermission.launch(Manifest.permission.CAMERA)
    }

    private val requestCameraPermission = registerForActivityResult(ActivityResultContracts.RequestPermission()){
            isSuccess : Boolean ->
        if(isSuccess){
            Log.d("Take Picture", "Permission granted")
            takePicture.launch(null)
        } else {
            Toast.makeText(applicationContext, "Camera has no permission", Toast.LENGTH_SHORT).show()
        }
    }

    private val takePicture = registerForActivityResult(ActivityResultContracts.TakePicturePreview()){
            bitmap: Bitmap? ->
        Log.d("Take Picture", "Show bitmap picture")
        imageView.setImageBitmap(bitmap)
    }
}