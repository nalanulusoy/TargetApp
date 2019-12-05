package com.example.targetapp.ui.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.fragment.app.Fragment

import kotlinx.android.synthetic.main.fragment_settings.*
import android.widget.Switch
import android.preference.PreferenceManager
import androidx.appcompat.app.AppCompatDelegate



class SettingsFragment : Fragment() {

    val NIGHT_MODE = "NIGHT_MODE"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(com.example.targetapp.R.layout.fragment_settings, container, false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (isNightModeEnabled()) {

            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            sw_night_mode.setChecked(true)
        } else {

            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

            sw_night_mode.setChecked(false)
        }

        sw_night_mode.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
            // do something, the isChecked will be
            // true if the switch is in the On position
            checkNighModeSwitch(sw_night_mode)
        })


    }



    fun isNightModeEnabled(): Boolean {
        val mPrefs = PreferenceManager.getDefaultSharedPreferences(context)
        return mPrefs.getBoolean(NIGHT_MODE, false)
    }

    fun setIsNightModeEnabled(isNightModeEnabled: Boolean) {
        val mPrefs = PreferenceManager.getDefaultSharedPreferences(context)
        val editor = mPrefs.edit()
        editor.putBoolean(NIGHT_MODE, isNightModeEnabled)
        editor.apply()
    }

    fun checkNighModeSwitch(switchCompat: Switch) {
        if (sw_night_mode.isChecked) {
            setIsNightModeEnabled(true)
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)

        } else {
            setIsNightModeEnabled(false)
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }

    }


}