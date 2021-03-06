package com.example.jrhapplication.ui

import android.graphics.drawable.Drawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.example.jrhapplication.R
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.drawee.backends.pipeline.PipelineDraweeController
import com.facebook.drawee.controller.AbstractDraweeController
import com.facebook.drawee.controller.ControllerListener
import com.facebook.drawee.interfaces.DraweeController
import com.facebook.drawee.view.SimpleDraweeView
import com.facebook.imagepipeline.common.ImageDecodeOptions
import com.facebook.imagepipeline.request.ImageRequest
import com.facebook.imagepipeline.request.ImageRequestBuilder
import kotlinx.android.synthetic.main.activity_fesco.*
import pl.droidsonroids.gif.GifDrawable

class FrescoActivity : AppCompatActivity() {


    val gifs = mutableListOf("11", "22", "33", "44", "55")
    val webps = mutableListOf("1", "2", "3", "4", "5")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fesco)


        sdv1.apply {
            loadGifFromAsset(sdv1, gifs[0])
            loadGifFromAsset(sdv2, gifs[1])
            loadGifFromAsset(sdv3, gifs[2])
            loadGifFromAsset(sdv4, gifs[3])
            loadGifFromAsset(sdv5, gifs[4])
            postDelayed({ sdv1.visibility = View.GONE }, 2000)
            postDelayed({ sdv2.visibility = View.GONE }, 4000)
            postDelayed({ sdv3.visibility = View.GONE }, 6000)
            postDelayed({ sdv4.visibility = View.GONE }, 8000)
        }


        sdv.apply {
            loadGifFromAsset(sdv, gifs[0])
            postDelayed({ loadGifFromAsset(sdv, gifs[1]) }, 2000)
            postDelayed({ loadGifFromAsset(sdv, gifs[2]) }, 4000)
            postDelayed({ loadGifFromAsset(sdv, gifs[3]) }, 6000)
            postDelayed({ loadGifFromAsset(sdv, gifs[4]) }, 8000)
        }

        val c1 = load(gifs[0])
        val c2 = load(gifs[1])
        val c3 = load(gifs[2])
        val c4 = load(gifs[3])
        val c5 = load(gifs[4])

        sdvx.apply {
            sdvx.controller = c1
            postDelayed({sdvx.controller = c2 }, 2000)
            postDelayed({sdvx.controller = c3  }, 4000)
            postDelayed({ sdvx.controller = c4 }, 6000)
            postDelayed({ sdvx.controller = c5 }, 8000)
        }

        sgp.apply {
            loadAsset(sgp,gifs[0])
            postDelayed({loadAsset(sgp,gifs[1])}, 2000)
            postDelayed({loadAsset(sgp,gifs[2]) }, 4000)
            postDelayed({ loadAsset(sgp,gifs[3])}, 6000)
            postDelayed({ loadAsset(sgp,gifs[4]) }, 8000)
        }




        webp1.apply {
            loadGifFromWebp(webp1, webps[0])
            loadGifFromWebp(webp2, webps[1])
            loadGifFromWebp(webp3, webps[2])
            loadGifFromWebp(webp4, webps[3])
            loadGifFromWebp(webp5, webps[4])
            postDelayed({ webp1.visibility = View.GONE }, 2000)
            postDelayed({ webp2.visibility = View.GONE }, 4000)
            postDelayed({ webp3.visibility = View.GONE }, 6000)
            postDelayed({ webp4.visibility = View.GONE }, 8000)
        }
    }

    fun loadAsset(img:ImageView,assetGifName: String){

        img.setImageDrawable(GifDrawable(assets,"$assetGifName.gif"))

    }

}

fun loadGifFromAsset(draweeView: SimpleDraweeView, assetGifName: String) {
    val uri = "asset:///$assetGifName.gif"


    val gifController = Fresco.newDraweeControllerBuilder()
        .setUri(Uri.parse(uri))
        .setAutoPlayAnimations(true)
        .build()
    draweeView.controller = gifController
}

fun load(assetGifName: String): DraweeController {
    val uri = "asset:///$assetGifName.gif"
    val imgDecode =
        ImageDecodeOptions.newBuilder().setDecodeAllFrames(true).setDecodePreviewFrame(true).build()
    return Fresco.newDraweeControllerBuilder()
        .setImageRequest(
            ImageRequestBuilder.newBuilderWithSource(Uri.parse(uri))
                .setImageDecodeOptions(imgDecode).build()
        )
        .setAutoPlayAnimations(true)
        .build()

}




fun loadGifFromWebp(draweeView: SimpleDraweeView, assetGifName: String) {
    val uri = "asset:///$assetGifName.webp"
    val gifController = Fresco.newDraweeControllerBuilder()
        .setUri(Uri.parse(uri))
        .setAutoPlayAnimations(true)
        .build()
    draweeView.controller = gifController
}