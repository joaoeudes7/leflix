package com.jedev.leflix.service.API.Entities;

import java.util.List;

public class SaleInfo {
    public String country;
    public String saleability;
    public boolean isEbook;
    public ListPrice listPrice;
    public RetailPrice retailPrice;
    public String buyLink;
    public List<Offer> offers;
}
