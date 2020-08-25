package com.example.jrhapplication.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.jrhapplication.R
import com.jrhlive.library.argument
import com.jrhlive.library.put

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [vpFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class vpFragment : Fragment() {
    private var param1: String by argument()
    private var param2: String by argument()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_vp, container, false)
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: Int) =
            vpFragment().apply {
                this.param1 = param1
                this.param2
            }
    }
}
