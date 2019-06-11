package com.jedev.leflix.model

import com.google.firebase.Timestamp


class ReadingBook {
    lateinit var id: String
    var book: Book? = null
    var progress: Double = 0.0
    var dateInit = Timestamp.now()
    var dataFinal: Timestamp? = null
    var usedMetrics: Boolean = true
    var pageReads: Int = 0
}
