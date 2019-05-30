package com.jedev.leflix.model

data class Book(var id: String,
                var title: String,
                var subtitle: String,
                var author: String,
                var publish_company: String,
                var synopsis: String,
                var pages: Int = 0,
                var chapters: Int = 0,
                var value_review: Double,
                var total_reviews: Int)