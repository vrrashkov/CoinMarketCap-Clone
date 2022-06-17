package com.vrashkov.coinmarketcapclone.core.util

import timber.log.Timber

inline fun <T> T.log(block: () -> String): T {
    println("${block()}${": $this"}")
    //Timber.d("${block()}${": $this"}")
    return this
}

class ReleaseTimberTree: Timber.Tree() {
    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
    }
}