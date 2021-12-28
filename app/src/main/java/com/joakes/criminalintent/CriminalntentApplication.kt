package com.joakes.criminalintent

import android.app.Application

class CriminalntentApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        CrimeRepository.initalize(this)
    }
}