package ua.kpi.comsys.io8303.model;

import java.util.ArrayList;

public class SearchItem {
    private String error;
    private String total;
    private String page;
    ArrayList<BookItem> books;

    public  ArrayList<BookItem> getBooks() { return books; }
}
