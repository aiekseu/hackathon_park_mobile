package com.sconzo.hackathon_park_mobile.fAboutAttraction

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.sconzo.hackathon_park_mobile.R
import kotlinx.android.synthetic.main.row_attraction.*


class AboutAttractionFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.row_attraction, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        attr_btn_buyTicket.setOnClickListener {
            findNavController().navigate(R.id.action_aboutAttractionFragment_to_buyFragment)
        }
    }

}