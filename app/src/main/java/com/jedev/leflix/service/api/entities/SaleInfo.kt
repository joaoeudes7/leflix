package com.jedev.leflix.service.api.entities

data class SaleInfo(var country: String,
                    var saleability: String,
                    var isEbook: Boolean,
                    var listPrice: ListPrice,
                    var retailPrice: RetailPrice,
                    var buyLink: String,
                    var offers: List<Offer>)
