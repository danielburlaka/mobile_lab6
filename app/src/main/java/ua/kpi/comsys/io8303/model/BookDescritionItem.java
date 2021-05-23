package ua.kpi.comsys.io8303.model;

public class BookDescritionItem {

    private String title;
    private String subtitle;
    private String authors;
    private String publisher;
    private String isbn13;
    private String pages;
    private String year;
    private String rating;
    private String desc;
    private String price;
    private String image;

    public String getTitle() {
        return title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public String getAuthors() {
        return authors;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getIsbn13() {
        return isbn13;
    }

    public String getPages() {
        return pages;
    }

    public String getYear() {
        return year;
    }

    public String getRating() {
        return rating;
    }

    public String getDesc() {
        return desc;
    }

    public String getPrice() {
        return price;
    }

    public String getImage() {
        return image;
    }

    public String toString() {
        return "Title: " + title +
                "\nSubtitle: " + subtitle +
                "\nDescription: " + desc +
                "\n\nAuthors: " + authors +
                "\nPublisher: " + publisher +
                "\n\nPages: " + pages +
                "\nYear: " + year +
                "\nRating: " + rating;
    }
}
