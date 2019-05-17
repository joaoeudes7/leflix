package com.jedev.leflix.service.api.entities

interface Item {
    var kind: String
    var id: String
    var etag: String
    var selfLink: String
    var volumeInfo: VolumeInfo
    var saleInfo: SaleInfo
    var accessInfo: AccessInfo
    var searchInfo: SearchInfo
}
