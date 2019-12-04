package com.example.targetapp.ui.fragment


import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.example.targetapp.R
import com.example.targetapp.Utils
import kotlinx.android.synthetic.main.fragment_new_add_task.*




class AddNewTaskFragment : Fragment() {

   var targetDate : Long = 0
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_new_add_task, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)



        button_save.setOnClickListener {
            if (TextUtils.isEmpty(edit_target.text) || targetDate.equals("") ) {
                Toast.makeText(
                    context,
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG
                ).show()

            } else {
                val targetText = edit_target.text.toString()
                targetDate =calendarView.date
                var bundle = bundleOf("targetArgs" to targetText,"targetArgsDate" to targetDate)

                view?.findNavController()?.navigate(com.example.targetapp.R.id.action_addNewTaskFragment_to_bottomNavFragmentAdd, bundle)

                Utils.hideSoftKeyboard(view)

            }

        }
    }


}



