package com.jedev.leflix.model

import com.jedev.leflix.utils.PRIVACY

class Group : IEntity {
    override lateinit var id: String
    lateinit var name: String
    lateinit var foto: String
    lateinit var description: String
    lateinit var privacy: PRIVACY

    constructor()

    constructor(id: String, name: String, foto: String, description: String, privacy: PRIVACY) : this() {
        this.id = id
        this.name = name
        this.foto = foto
        this.description = description
        this.privacy = privacy
    }

    fun save() {
        GrupoService().api.push().setValue(this)
    }

    fun addAttach(storeLink: String) {
        this.foto = (storeLink)
    }
}

