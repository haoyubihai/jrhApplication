package com.example.jrhapplication.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.jrhapplication.R
import com.jrhlive.library.argument
import kotlinx.android.synthetic.main.fragment_image.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ImageFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ImageFragment : Fragment() {


    var url by argument<String>()
    var type by argument<Int>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_image, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        when(type){
            0->{
                loadByGlide()
            }
            else->{
                loadByFresco()
            }
        }
    }

    private fun loadByFresco() {
        sdv.visibility = View.VISIBLE
        img.visibility = View.GONE

    }

    private fun loadByGlide() {
        sdv.visibility = View.GONE
        img.visibility = View.VISIBLE

    }

    companion object {

        @JvmStatic
        fun newInstance(url: String,type:Int) =
            ImageFragment().apply {
                this.url = url
                this.type = type
            }
    }
}