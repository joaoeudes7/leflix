package com.jedev.leflix.model

class User : IEntity {
    override var id: String? = null
    var name: String? = null
    var email: String? = null
    var password: String? = null
    var active: Boolean? = null
    var createdOn: String? = null
    var ofenciveDays: Int = 0
    var lastDateOfensive: String? = null

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

    constructor(name: String, email: String, password: String, active: Boolean?, createdOn: String, ofenciveDays: Int, lastDateOfensive: String) {
        this.name = name
        this.email = email
        this.password = password
        this.active = active
        this.createdOn = createdOn
        this.ofenciveDays = ofenciveDays
        this.lastDateOfensive = lastDateOfensive
    }

    constructor(id: String, name: String, email: String, password: String, active: Boolean?, createdOn: String, ofenciveDays: Int, lastDateOfensive: String) {
        this.id = id
        this.name = name
        this.email = email
        this.password = password
        this.active = active
        this.createdOn = createdOn
        this.ofenciveDays = ofenciveDays
        this.lastDateOfensive = lastDateOfensive
    }


}
