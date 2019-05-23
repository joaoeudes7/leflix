package com.jedev.leflix.model

import com.google.firebase.Timestamp

class User : IEntity {
    override lateinit var id: String
    lateinit var name: String
    lateinit var email: String
    lateinit var password: String
    val createdOn: Timestamp = Timestamp.now()
    var active: Boolean = true
    var ofenciveDays: Int = 0
    var lastDateOfensive: Timestamp? = null

    constructor()

    constructor(name: String, email: String, password: String) {
        this.name = name
        this.email = email
        this.password = password
    }

    constructor(email: String, password: String) {
        this.email = email
        this.password = password
    }

    constructor(id: String, name: String, email: String, password: String) {
        this.id = id
        this.name = name
        this.email = email
        this.password = password
    }
}
