package com.example.targetapp

import android.content.Context
import android.preference.PreferenceManager
import android.view.View
import android.view.inputmethod.InputMethodManager




object Utils {

    fun hideSoftKeyboard(view: View?) {
        if (view != null) {
            val inputManager =
                view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputManager?.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }


    object WHERE_TYPE {
        val TARGET_LİST_FRAGMENT: String = "TARGET_LİST_FRAGMENT"

        val COMPLETED_LİST_FRAGMENT: String = "COMPLETED_LİST_FRAGMENT"

        val ADD_TASK_FRAGMENT: String = "ADD_TASK_FRAGMENT"
    }









}