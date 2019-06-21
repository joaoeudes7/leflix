package com.jedev.leflix.model

import com.google.firebase.Timestamp

class Comment: IEntity {
    override var id: String? = null
    var user: User? = null
    var content: String? = null
    var reviewValue: Double? = null
    var updatedOn = Timestamp.now()
}