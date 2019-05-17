package com.jedev.leflix.service.api.entities

interface Volume {
    var kind: String
    var totalItems: Int
    var items: List<Item>
}