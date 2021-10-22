package com.example.jrhapplication.widgets.atmosphere

import android.graphics.Bitmap
import android.graphics.PointF

data class AtmosphereItem (var bitmap: Bitmap,var alpha:Float=0f,var scale:Float=1f,var pointF:PointF){

    fun getBitmapWith() = bitmap.width
    fun getBitmapHeight() = bitmap.height
}