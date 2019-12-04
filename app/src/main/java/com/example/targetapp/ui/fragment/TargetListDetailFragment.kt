package com.example.targetapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.targetapp.R
import com.example.targetapp.ui.viewModel.TargetDetailListViewModel
import kotlinx.android.synthetic.main.fragment_targetlist.*
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.example.targetapp.TargetModel


class TargetListDetailFragment : Fragment() {
    lateinit var targetDetailListViewModel: TargetDetailListViewModel
    var targetArgsId: Int = 0
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_targetlist, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        targetArgsId = arguments?.getInt("targetArgsId")!!

        targetDetailListViewModel =
            ViewModelProvider(this).get(TargetDetailListViewModel::class.java)


        targetDetailListViewModel.findByIdTarget(targetArgsId)
            .observe(viewLifecycleOwner, Observer {

                if (it != null) {
                    tv_targetName.text = it.target


                    if (it.targetDate != null) {
                        calendarViewDetail.date = it.targetDate.toLong()
                    }

                    if (it.targetStatus != null) {
                        checkTrueFalseListener(it.targetStatus)
                    }

                }


            })
        checkButtonClickListener()

    }


    fun fetchStatusTarget(): Boolean {
        if (checkedTrue.isChecked) {
            return true
        }
        return false
    }


    fun checkButtonClickListener() {

        bt_update.setOnClickListener {


            var targetName = tv_targetName.text.toString()
            var targetStatus = fetchStatusTarget()
            var targetDate = calendarViewDetail.date


            var targetModel = TargetModel(targetName, targetStatus, targetDate)



            if (targetModel != null) {
                targetDetailListViewModel.updateTarget(targetModel)
            }


            view?.findNavController()
                ?.navigate(R.id.action_targetListDetailFragment_to_bottomNavFragmentHome)


        }


    }


    fun checkTrueFalseListener(status: Boolean) {

        if (status) {
            checkedTrue.setChecked(true)
            checkedFalse.setChecked(false)
        } else {
            checkedTrue.setChecked(false)
            checkedFalse.setChecked(true)
        }

        checkedTrue.setOnClickListener {

            if (checkedTrue.isChecked) {

                checkedTrue.setChecked(false)
                checkedFalse.setChecked(true)

            } else {
                checkedFalse.setChecked(false)
                checkedTrue.setChecked(true)

            }
        }

        checkedFalse.setOnClickListener {

            if (checkedFalse.isChecked) {
                checkedFalse.setChecked(true)
                checkedTrue.setChecked(false)

            } else {
                checkedTrue.setChecked(false)
                checkedFalse.setChecked(true)

            }

        }

    }

}