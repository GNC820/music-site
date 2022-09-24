package model;

public class Order {

    private Integer id;
    private Integer songId;
    private Integer userId;
    private String price;
    private Integer quantity;
    private Integer total;

    public Order(Integer id, Integer songId, Integer userId, String price, Integer quantity, Integer total) {
        this.id = id;
        this.songId = songId;
        this.userId = userId;
        this.price = price;
        this.quantity = quantity;
        this.total = total;
    }

    public Order(Integer songId, Integer userId, String price, Integer quantity, Integer total) {
        this.songId = songId;
        this.userId = userId;
        this.price = price;
        this.quantity = quantity;
        this.total = total;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSongId() {
        return songId;
    }

    public void setSongId(Integer songId) {
        this.songId = songId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Order{" + "id=" + id + ", songId=" + songId + ", userId=" + userId + ", price=" + price + ", quantity=" + quantity + ", total=" + total + '}';
    }
    
}
