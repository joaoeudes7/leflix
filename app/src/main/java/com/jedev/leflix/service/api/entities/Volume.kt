package com.jedev.leflix.service.api.entities

data class Volume(var kind: String, var totalItems: Int, var items: List<Item>)