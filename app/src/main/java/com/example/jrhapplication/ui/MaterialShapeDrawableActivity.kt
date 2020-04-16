package com.example.jrhapplication.ui

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.core.graphics.ColorUtils
import com.example.jrhapplication.R
import com.google.android.material.bottomappbar.BottomAppBarTopEdgeTreatment
import com.google.android.material.shape.*
import com.google.android.material.shape.MaterialShapeDrawable.SHADOW_COMPAT_MODE_ALWAYS
import kotlinx.android.synthetic.main.activity_material_shape_drawable.*
import java.util.*

class MaterialShapeDrawableActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_material_shape_drawable)

        displayDefault()
        displayTvRound()
        displayTopRound()

        tvrt.showShape(
            ShapeAppearanceModel.builder()
                .setTopLeftCorner(CornerFamily.ROUNDED, dp())
                .setTopRightCorner(CornerFamily.CUT, dp())
                .setBottomLeftCorner(CornerFamily.CUT, dp())
                .setBottomRightCorner(CornerFamily.ROUNDED, dp())
                .build()
        )

        with(tv1) {
            post {
                showShape(
                    ShapeAppearanceModel.builder()
                        .setTopLeftCorner(CornerFamily.CUT, width / 4.0f)
                        .setTopRightCorner(CornerFamily.CUT, width / 4.0f)
                        .setBottomLeftCorner(CornerFamily.CUT, width / 4.0f)
                        .setBottomRightCorner(CornerFamily.CUT, width / 4.0f)
                        .build()
                )
            }
        }
        with(tv2) {
            post {
                showShape(
                    ShapeAppearanceModel.builder()
                        .setTopLeftCorner(CornerFamily.CUT, width / 2.0f)
                        .setTopRightCorner(CornerFamily.CUT, width / 2.0f)
                        .setBottomLeftCorner(CornerFamily.CUT, width / 2.0f)
                        .setBottomRightCorner(CornerFamily.CUT, width / 2.0f)
                        .build()
                )
            }
        }

        with(tv3) {
            post {
                showShape(
                    ShapeAppearanceModel.builder()
                        .setTopLeftCorner(CornerFamily.CUT, width / 2.0f)
                        .setTopRightCorner(CornerFamily.CUT, width / 3.0f)
                        .setBottomLeftCorner(CornerFamily.CUT, width / 4.0f)
                        .setBottomRightCorner(CornerFamily.CUT, width / 5.0f)
                        .build()
                )
            }
        }

        with(tv4) {
            post {
                showShape(
                    ShapeAppearanceModel.builder()
                        .setTopLeftCorner(CornerFamily.ROUNDED, height / 2.0f)
                        .setBottomRightCorner(CornerFamily.ROUNDED, height / 2.0f)
                        .build()
                )
            }
        }

        with(lay1) {
            post {
                showShape(
                    ShapeAppearanceModel.builder()
                        .setTopLeftCorner(CornerFamily.ROUNDED, height / 2.0f)
                        .setBottomRightCorner(CornerFamily.ROUNDED, height / 2.0f)
                        .build()
                )
            }
        }

        with(tv6) {
            post {
                showShape(
                    ShapeAppearanceModel.builder()
                        .setTopLeftCorner(CornerFamily.ROUNDED, height / 2.0f)
                        .setBottomRightCorner(CornerFamily.ROUNDED, height / 2.0f)
                        .setAllEdges(BottomAppBarTopEdgeTreatment(dp(), dp() * 2, dp()))
                        .build()
                )
            }
        }

        with(tv7) {
            post {
                showShape(
                    ShapeAppearanceModel.builder()
                        .setAllEdges(TriangleEdgeTreatment(dp(), true))
                        .build()
                )
            }
        }
        with(tv8) {
            post {
                showShape(
                    ShapeAppearanceModel.builder()
                        .setAllCorners(CutoutCornersTreatment(dp()/5))
                        .build()
                )
            }
        }

        with(tv9) {
            post {
                showShape(
                    ShapeAppearanceModel.builder()
                        .setAllEdges(CurvedEdgeTreatment(dp()*3))
                        .build()
                )
            }
        }
        with(tv11) {
            post {
                showShape(
                    ShapeAppearanceModel.builder()
                        .setAllCorners(CutoutCornersTreatment(dp()/5))
                        .setAllEdges(CurvedEdgeTreatment(dp()/5))
                        .build()
                )
            }
        }
        with(tv10) {
            post {
                showShape(
                    ShapeAppearanceModel.builder()
                        .setAllEdges(
                            OffsetEdgeTreatment(
                                TriangleEdgeTreatment(dp(), true),
                                dp() / 3
                            )
                        )
                        .build()
                )
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun displayTopRound() {
        val shapeModel = ShapeAppearanceModel.builder()
            .setTopLeftCornerSize(dp())
            .setTopRightCornerSize(dp())
            .build()
        tvlt.showShape(shapeModel)
    }

    private fun dp() = resources.getDimension(R.dimen.dp_14)


    private fun displayDefault() {
        tvDefault.setTextColor(Color.WHITE)
        tvDefault.background = MaterialShapeDrawable()
    }

    @SuppressLint("RestrictedApi")
    private fun displayTvRound() {

        tvRound.post {

            tvRound.background = MaterialShapeDrawable().apply {
                setTint(Color.RED)
                setCornerSize(tvRound.width / 2f)
            }

        }

    }


}

class CutoutCornersTreatment(val size: Float) : CornerTreatment() {
    override fun getCornerPath(angle: Float, interpolation: Float, shapePath: ShapePath) {
        shapePath.reset(0.0f, size * interpolation)
        shapePath.lineTo(size * interpolation, size * interpolation)
        shapePath.lineTo(size * interpolation, 0f)
    }
}

class CurvedEdgeTreatment(val size: Float) : EdgeTreatment() {
    override fun getEdgePath(length: Float, interpolation: Float, shapePath: ShapePath) {
        shapePath.cubicToPoint(length / 2f, size * interpolation, length, 0f,0f,length/2)
    }
}

@SuppressLint("RestrictedApi")
fun View.showShape(shapeModel: ShapeAppearanceModel) {
    this.background = MaterialShapeDrawable(shapeModel).apply {
        setTint(
            Color.parseColor("#7800da")
        )
        shadowCompatibilityMode = SHADOW_COMPAT_MODE_ALWAYS
        elevation = resources.getDimension(R.dimen.dp_10)
        setShadowColor(Color.RED)
        setShadowBitmapDrawingEnable(true)
    }
}
