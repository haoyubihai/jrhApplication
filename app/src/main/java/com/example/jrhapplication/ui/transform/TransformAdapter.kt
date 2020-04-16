package com.example.jrhapplication.ui.transform

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.annotation.GlideModule
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.jrhapplication.R
import com.example.jrhapplication.bean.Poster
import com.skydoves.transformationlayout.TransformationLayout
import kotlinx.android.synthetic.main.item_transform.view.*

/**
 ***************************************
 * 项目名称:jrhApplication
 * @Author jiaruihua
 * 邮箱：jiaruihua@ksjgs.com
 * 创建时间: 2020/4/10     5:10 PM
 * 用途:
 ***************************************
 */
class TransformAdapter( data: MutableList<Poster>?) :
    BaseQuickAdapter<Poster, BaseViewHolder>(R.layout.item_transform, data) {
    override fun convert(holder: BaseViewHolder, item: Poster) {

        holder.setText(R.id.title,item.name)
        val img = holder.getView<ImageView>(R.id.ivBg)

        val trans = holder.getView<TransformationLayout>(R.id.item_trans_lay)
        Glide.with(context).load(item.poster).into(img)


        holder.itemView.apply {
            setOnClickListener {
                PosterDetailActivity.startActivity(context,trans,item)
            }

        }
    }
}