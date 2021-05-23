package ua.kpi.comsys.io8303.model;

public class PicItemJson {
    int id;
    String pageURL;
    String type;
    String tags;
    String previewURL;
    int previewWidth;
    int previewHeight;
    String webformatURL;
    int webformatWidth;
    int webformatHeight;
    String largeImageURL;
    int imageWidth;
    int imageHeight;
    int imageSize;
    int views;
    int downloads;
    int favorites;
    int likes;
    int comments;
    int user_id;
    String user;
    String userImageURL;

    public String getImage() {return webformatURL;}
}
