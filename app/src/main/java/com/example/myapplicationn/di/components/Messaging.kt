package com.example.myapplicationn.di.components

import android.content.Context
import es.dmoral.toasty.Toasty

class Messaging(private val context: Context) {

    fun showErrorToast(message: String) {
        Toasty.error(context, message, Toasty.LENGTH_SHORT).show()
    }
}