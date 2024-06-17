package com.example.hap6_binar.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.io.File
import java.io.FileOutputStream

class BlurWorker(
    private val context: Context,
    workerParams: WorkerParameters
) : Worker(context, workerParams) {

    override fun doWork(): Result {
        val imagePath = inputData.getString("image_path")
        if (imagePath != null) {
            val file = File(imagePath)
            val bitmap = BitmapFactory.decodeFile(file.absolutePath)
            val blurredBitmap = blurBitmap(bitmap)
            saveBitmapToFile(blurredBitmap, file)
            return Result.success()
        }
        return Result.failure()
    }

    private fun blurBitmap(bitmap: Bitmap): Bitmap {
        // Perform the blur operation here
        // This is a placeholder, actual blur implementation required
        return bitmap
    }

    private fun saveBitmapToFile(bitmap: Bitmap, file: File) {
        FileOutputStream(file).use { out ->
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out)
        }
    }
}
