package ua.kpi.comsys.io8303.model;

import java.util.ArrayList;

public class PicSearchJson {
    int total;
    int totalHits;
    ArrayList<PicItemJson> hits;

    public ArrayList<PicItemJson> getPics() {return hits;}
}

