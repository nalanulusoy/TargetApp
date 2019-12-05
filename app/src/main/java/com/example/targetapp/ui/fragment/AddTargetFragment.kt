package com.example.targetapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.targetapp.*
import com.example.targetapp.ui.adapter.TargetListAdapter
import kotlinx.android.synthetic.main.fragment_add_task.*


class AddTargetFragment : Fragment() {
    private lateinit var addtaskNewModel: AddNewTargetViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_task, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // Get a new or existing ViewModel from the ViewModelProvider.
        addtaskNewModel = ViewModelProvider(this).get(AddNewTargetViewModel::class.java)

        val adapter = context?.let {
            TargetListAdapter(
                it,
                Utils.WHERE_TYPE.ADD_TASK_FRAGMENT
            )
        }
        recyclerview.adapter = adapter
        recyclerview.layoutManager = LinearLayoutManager(context)

        addtaskNewModel.allTargets.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            adapter?.setTask(it)

        })
        val targetValue= arguments?.getString("targetArgs")
        val targetArgsDate= arguments?.getLong("targetArgsDate")

        val target = targetValue?.let { targetArgsDate?.let { it1 -> TargetModel(it, false, it1) } }
        if (target != null) {
            addtaskNewModel.insert(target)
        }
            fab.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_bottomNavFragmentAdd_to_addNewTaskFragment))




    }
}


