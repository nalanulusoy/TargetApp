package com.example.targetapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.targetapp.R
import com.example.targetapp.Utils
import com.example.targetapp.ui.adapter.TargetListAdapter
import com.example.targetapp.ui.viewModel.HomeTargetListViewModel
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {
    lateinit var homeTargetListViewModel: HomeTargetListViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home,container,false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        homeTargetListViewModel = ViewModelProvider(this).get(HomeTargetListViewModel::class.java)

        val adapter = context?.let {
            TargetListAdapter(
                it,
                Utils.WHERE_TYPE.TARGET_LİST_FRAGMENT
            )
        }
        rec_targetList.adapter = adapter
        rec_targetList.layoutManager = LinearLayoutManager(context)
        homeTargetListViewModel.allTargets.observe(viewLifecycleOwner, Observer {
            adapter?.setTask(it)
            rec_targetList.adapter

        })

    }


}