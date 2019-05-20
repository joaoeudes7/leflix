package com.jedev.leflix.service.api.entities

interface VolumeInfo {
    var title: String
    var subtitle: String
    var authors: List<String>
    var publisher: String
    var publishedDate: String
    var description: String
    var industryIdentifiers: List<IndustryIdentifier>
    var readingModes: ReadingModes
    var pageCount: Int
    var printType: String
    var categories: List<String>
    var averageRating: Double
    var ratingsCount: Int
    var maturityRating: String
    var allowAnonLogging: Boolean
    var contentVersion: String
    var panelizationSummary: PanelizationSummary
    var imageLinks: ImageLinks
    var language: String
    var previewLink: String
    var infoLink: String
    var canonicalVolumeLink: String
    var seriesInfo: SeriesInfo
}