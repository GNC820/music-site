package model;

public class Song {

    private Integer id;
    private String title;
    private String artist;
    private String price;
    private String description;
    private Integer salesAmount;

    public Song(Integer id, String title, String artist, String price, String description, Integer salesAmount) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.price = price;
        this.description = description;
        this.salesAmount = salesAmount;
    }

    public Song(String title, String artist, String price, String description, Integer salesAmount) {
        this.title = title;
        this.artist = artist;
        this.price = price;
        this.description = description;
        this.salesAmount = salesAmount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getSalesAmount() {
        return salesAmount;
    }

    public void setSalesAmount(Integer salesAmount) {
        this.salesAmount = salesAmount;
    }

    @Override
    public String toString() {
        return "Song{" + "id=" + id + ", title=" + title + ", artist=" + artist + ", price=" + price + ", description=" + description + ", salesAmount=" + salesAmount + '}';
    }
}
