package com.sconzo.hackathon_park_mobile.fBuy

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.sconzo.hackathon_park_mobile.R
import kotlinx.android.synthetic.main.fragment_buy.*
import moxy.MvpAppCompatFragment
import moxy.MvpView


class BuyFragment : MvpAppCompatFragment(), MvpView {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_buy, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        buying_btn_buy.setOnClickListener {
            findNavController().navigate(R.id.action_buyFragment_to_ticketFragment)
        }
    }


}