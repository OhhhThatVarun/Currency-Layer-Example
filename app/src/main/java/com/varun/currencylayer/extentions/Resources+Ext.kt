package com.varun.currencylayer.extentions

import android.content.res.Resources
import com.varun.currencylayer.BuildConfig
import com.varun.currencylayer.R
import java.util.*

fun Resources.getFlagResource(flagName: String): Int {
    val id = getIdentifier(
        "_${flagName.toLowerCase(Locale.ROOT)}",
        "drawable",
        BuildConfig.APPLICATION_ID
    )
    if (id == 0) {
        return R.drawable._no_flag
    }
    return id
}