package com.example.live_lib_views.textview

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ImageSpan
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.AppCompatTextView

class TagTextView(context: Context, attrs: AttributeSet?):AppCompatTextView(context, attrs) {


    fun setContentTags(content:String,tags:List<String>,tv:TextView){
        val sb = StringBuilder()
        tags.forEach{
            sb.append(it)
        }
        sb.append(content)

        val sp = SpannableString(sb)

        for(i in tags.indices){
            tv?.text =tags.getOrNull(i)
            val bmp = viewToBitmap(tv)
            bmp?.let {b ->
                val drawable = BitmapDrawable(b)
                drawable.setBounds(0,0,tv.width,tv.height)
                val span = ImageSpan(drawable,ImageSpan.ALIGN_BOTTOM)
                var start = getLastLength(tags,i)
                var end =  start+tags[i].length
                if(sp.length>=end){
                    sp.setSpan(span,start,end,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                }
            }
        }
        text = sp
        gravity = Gravity.TOP

    }

    private fun getLastLength(tags: List<String>, maxLen: Int): Int {
        var len = 0
        for( i in 0 until maxLen){
            len+=tags[i].length
        }

        return len
    }

    private fun viewToBitmap(view: View?): Bitmap? {

        view?.run {
            measure(MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED),MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED))
            layout(0,0,measuredWidth,measuredHeight)
            view.buildDrawingCache()
            val bitmap = view.getDrawingCache()
            return bitmap

        }
        return null
    }
}