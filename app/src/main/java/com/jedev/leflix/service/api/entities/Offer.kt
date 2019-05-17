package com.jedev.leflix.service.api.entities

interface Offer {
    var finskyOfferType: Int
    var listPrice: ListPrice
    var retailPrice: RetailPrice
    var giftable: Boolean
}
