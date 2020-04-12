package com.example.myapplicationn.di.components

import android.content.Context
import es.dmoral.toasty.Toasty

class Messaging(private val context: Context) {

    /**
     * @param type -> type of toast
     */
    fun showToast(type: String, message: String) {
        when(type){
            "error" ->  Toasty.error(context, message, Toasty.LENGTH_SHORT).show()
            "info" ->  Toasty.info(context, message, Toasty.LENGTH_SHORT).show()
            else ->  Toasty.normal(context, message, Toasty.LENGTH_SHORT).show()
        }
    }
}