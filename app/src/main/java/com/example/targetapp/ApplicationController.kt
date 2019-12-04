package com.example.targetapp

import android.app.Application
import android.content.Context

class ApplicationController :Application() {

    init {
        singleton = this
    }

    companion object {
        private var singleton: ApplicationController? = null

        fun applicationContext() : Context {
            return singleton!!.applicationContext
        }
    }


    override fun onCreate() {
        super.onCreate()

    }
}