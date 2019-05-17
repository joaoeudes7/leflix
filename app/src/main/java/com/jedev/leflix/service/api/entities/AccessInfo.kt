package com.jedev.leflix.service.api.entities

interface AccessInfo {
        val country: String
        val viewability: String
        val embeddable: Boolean
        val publicDomain: Boolean
        val textToSpeechPermission: String
        val epub: Epub
        val pdf: Pdf
        val webReaderLink: String
        val accessViewStatus: String
        val quoteSharingAllowed: Boolean
}
