package com.jedev.leflix.service.API.Entities;

import java.util.List;

public class VolumeInfo {
    public String title;
    public String subtitle;
    public List<String> authors;
    public String publisher;
    public String publishedDate;
    public String description;
    public List<IndustryIdentifier> industryIdentifiers;
    public ReadingModes readingModes;
    public Integer pageCount;
    public String printType;
    public List<String> categories;
    public Double averageRating;
    public Integer ratingsCount;
    public String maturityRating;
    public Boolean allowAnonLogging;
    public String contentVersion;
    public PanelizationSummary panelizationSummary;
    public ImageLinks imageLinks;
    public String language;
    public String previewLink;
    public String infoLink;
    public String canonicalVolumeLink;
    public SeriesInfo seriesInfo;
}
