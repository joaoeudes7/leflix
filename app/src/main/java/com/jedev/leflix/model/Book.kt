package com.jedev.leflix.model

class Book : IEntity {
    override lateinit var id: String
    private lateinit var name: String
    private lateinit var author: String
    private lateinit var publish_company: String
    private lateinit var synopsis: String
    private var pages: Int = 0
    private var chapters: Int = 0
}