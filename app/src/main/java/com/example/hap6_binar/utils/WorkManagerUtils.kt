package com.example.hap6_binar.utils

import android.content.Context
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf

object WorkManagerUtils {
    fun startBlurWork(context: Context, imagePath: String) {
        val blurRequest = OneTimeWorkRequestBuilder<BlurWorker>()
            .setInputData(workDataOf("image_path" to imagePath))
            .build()
        val enqueue = WorkManager.getInstance(context).enqueue(blurRequest)
    }
}
