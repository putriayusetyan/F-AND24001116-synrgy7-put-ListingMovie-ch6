package com.example.hap6_binar.presentation.ui.profile

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.work.*
import com.bumptech.glide.Glide
import com.example.hap6_binar.R
import com.example.hap6_binar.utils.ImageUtils
import com.example.hap6_binar.utils.Result
import dagger.hilt.android.AndroidEntryPoint
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class ProfileActivity : AppCompatActivity() {

    private val profileViewModel: ProfileViewModel by viewModels()
    private lateinit var usernameEditText: EditText
    private lateinit var saveButton: Button
    private lateinit var profileImageView: ImageView
    private lateinit var uploadButton: Button

    companion object {
        private const val PICK_IMAGE_REQUEST = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        usernameEditText = findViewById(R.id.usernameEditText)
        saveButton = findViewById(R.id.saveButton)
        profileImageView = findViewById(R.id.profileImageView)
        uploadButton = findViewById(R.id.uploadButton)

        profileViewModel.userName.observe(this, Observer { name ->
            usernameEditText.setText(name)
        })

        profileViewModel.userImage.observe(this, Observer { imagePath ->
            imagePath?.let {
                Glide.with(this).load(imagePath).into(profileImageView)
            }
        })

        saveButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            profileViewModel.saveUserName(username)
        }

        uploadButton.setOnClickListener {
            openImagePicker()
        }
    }

    private fun openImagePicker() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, PICK_IMAGE_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK) {
            val selectedImage: Uri? = data?.data
            selectedImage?.let {
                val bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, selectedImage)
                val file = saveImageToInternalStorage(bitmap)
                profileViewModel.saveUserImage(file.absolutePath)
                startBlurWork(file.absolutePath)
            }
        }
    }

    private fun saveImageToInternalStorage(bitmap: Bitmap): File {
        val file = File(filesDir, "profile_image.png")
        FileOutputStream(file).use { out ->
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out)
        }
        return file
    }

    private fun startBlurWork(imagePath: String) {
        val blurRequest = OneTimeWorkRequestBuilder<BlurWorker>()
            .setInputData(workDataOf("image_path" to imagePath))
            .setConstraints(Constraints.Builder().setRequiresCharging(true).build())
            .setBackoffCriteria(BackoffPolicy.LINEAR, OneTimeWorkRequest.DEFAULT_BACKOFF_DELAY_MILLIS, TimeUnit.MILLISECONDS)
            .build()
        WorkManager.getInstance(this).enqueue(blurRequest)
    }
}
