package com.muflihunnisa.indoquiz.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.muflihunnisa.indoquiz.R
import com.muflihunnisa.indoquiz.databinding.FragmentLoseBinding

class LoseFragment : Fragment() {

    private lateinit var loseBinding: FragmentLoseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        loseBinding = FragmentLoseBinding.inflate(inflater, container, false)
        return loseBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loseBinding.btnLose.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_loseFragment_to_startFragment))
    }

}