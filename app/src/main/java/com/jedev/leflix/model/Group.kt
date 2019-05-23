package com.jedev.leflix.model

import com.jedev.leflix.utils.PRIVACY

class Group : IEntity {
    override lateinit var id: String
    lateinit var name: String
    lateinit var description: String
    lateinit var privacy: PRIVACY
}
