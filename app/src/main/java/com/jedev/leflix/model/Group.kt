package com.jedev.leflix.model

import com.jedev.leflix.utils.PRIVACY

class Group : IEntity {
    override lateinit var id: String
    lateinit var name: String
    lateinit var photo: String
    lateinit var description: String
    lateinit var privacy: PRIVACY
    val members = mutableListOf<User>()

    constructor()

    constructor(name: String, description: String, photo: String, privacy: PRIVACY) : this() {
        this.name = name
        this.photo = photo
        this.description = description
        this.privacy = privacy
    }
}

