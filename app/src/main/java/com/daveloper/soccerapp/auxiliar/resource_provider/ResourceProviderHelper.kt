package com.daveloper.soccerapp.auxiliar.resource_provider

import android.content.Context
import com.daveloper.soccerapp.auxiliar.ext_fun.getStringResource
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