package com.jedev.leflix.service.api.entities

data class Offer(var finskyOfferType: Int, var listPrice: ListPrice, var retailPrice: RetailPrice, var giftable: Boolean)
