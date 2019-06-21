package com.jedev.leflix.model

import com.google.firebase.Timestamp

class User : IEntity {
    override var id: String? = null
    lateinit var name: String
    lateinit var email: String
    var photoUrl: String? = null
    val createdOn: Timestamp = Timestamp.now()
    var ofenciveDays: Int = 0
    var lastDateOfensive: Timestamp? = null

    constructor()

    constructor(name: String, email: String) {
        this.name = name
        this.email = email
    }

    constructor(id: String, name: String, email: String) {
        this.id = id
        this.name = name
        this.email = email
    }
}
