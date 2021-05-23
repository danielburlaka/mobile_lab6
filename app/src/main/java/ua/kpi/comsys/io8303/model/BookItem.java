package ua.kpi.comsys.io8303.model;
public class BookItem {

    private String title;
    private String subtitle;
    private String isbn13;
    private String price;
    private String image;

    public String getTitle() {
        return title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public String getIsbn13() {
        return isbn13;
    }

    public String getPrice() {
        return price;
    }

    public String getImage() {
        return image;
    }

    public String toString() {
        return "{\"title\":\"" + title + "\"," +
                "\"subtitle\":\"" + subtitle + "\"," +
                "\"isbn13\": \""+ isbn13 + "\"," +
                "\"price\":\"" + price + "\"," +
                "\"image\":\"" + image + "\"}";
    }
}
