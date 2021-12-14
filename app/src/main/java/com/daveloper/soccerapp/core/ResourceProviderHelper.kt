package com.daveloper.soccerapp.core

import android.content.Context
import com.daveloper.soccerapp.auxiliar.ext_fun.getStringResource
import java.util.concurrent.CopyOnWriteArrayList
import javax.inject.Inject


class ResourceProviderHelper @Inject constructor(
    private val giveMeAppContext: Context
) {
    fun getStringResource (
        strResource: Int
    ): String {
        val context = giveMeAppContext
        return context.getStringResource(strResource)
    }
}