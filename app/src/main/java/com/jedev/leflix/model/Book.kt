package com.jedev.leflix.model

import com.jedev.leflix.service.api.entities.ImageLinks
import com.jedev.leflix.service.api.entities.Item

class Book(): IEntity {
    override var id: String? = null
    var title: String? = null
    var subtitle: String? = null
    var thumbnails: ImageLinks? = null
    var authors: List<String>? = null
    var publishCompany: String? = null
    var synopsis: String? = null
    var pages: Int = 0
    var chapters: Int = 0
    var valueReview: Double = 5.0
    var totalReviews: Int = 0
    var comments = mutableListOf<Comment>()

    constructor(title: String, subtitle: String, authors: List<String>, publishCompany: String, synopsis: String, pages: Int = 0, chapters: Int = 0, valueReview: Double, totalReviews: Int): this() {
        this.title = title
        this.subtitle = subtitle
        this.authors = authors
        this.publishCompany = publishCompany
        this.synopsis = synopsis
        this.pages = pages
        this.chapters = chapters
        this.valueReview = valueReview
        this.totalReviews = totalReviews
    }

    fun applyData(itemBook: Item): Book {
        this.id = itemBook.id
        this.title = itemBook.volumeInfo.title
        this.pages = itemBook.volumeInfo.pageCount
        this.authors = itemBook.volumeInfo.authors
        this.subtitle = itemBook.volumeInfo.subtitle
        this.synopsis = itemBook.volumeInfo.description
        itemBook.volumeInfo.seriesInfo?.let {
            this.chapters = it.volumeSeries.size
        }

        itemBook.volumeInfo.imageLinks?.let {
            val smallThumbnail = it.smallThumbnail.replace("http://", "https://")
            val thumbnail = it.thumbnail.replace("http://", "https://")

            this.thumbnails = ImageLinks(smallThumbnail, thumbnail)
        }

        return this
    }

    fun addComment(comment: Comment) {
        // add validations
        this.comments.add(comment)
    }
}
