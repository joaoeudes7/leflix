package com.jedev.leflix.model

class Book() {
    var id: String? = null
    var title: String? = null
    var subtitle: String? = null
    var author: String? = null
    var publish_company: String? = null
    var synopsis: String? = null
    var pages: Int = 0
    var chapters: Int = 0
    var value_review: Double = 0.0
    var total_reviews: Int = 0

    constructor(title: String, subtitle: String, author: String, publish_company: String, synopsis: String, pages: Int = 0, chapters: Int = 0, value_review: Double, total_reviews: Int): this() {
        this.title = title
        this.subtitle = subtitle
        this.author = author
        this.publish_company = publish_company
        this.synopsis = synopsis
        this.pages = pages
        this.chapters = chapters
        this.value_review = value_review
        this.total_reviews = total_reviews
    }
}
