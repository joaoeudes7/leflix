package com.jedev.leflix.service.api.entities

interface SeriesInfo {
    var kind: String
    var shortSeriesBookTitle: String
    var bookDisplayNumber: String
    var volumeSeries: List<VolumeSery>
}
