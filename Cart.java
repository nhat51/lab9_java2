package lab08.Models;

public class Cart {
    int bookID;
    String title;
    int amount;
    double price;
    int qty;
    int discount;
    public Cart(){
        this.bookID = 0;
        this.title = "";
        this.amount = 0;
        this.qty = 0;
        this.discount = 0;
    }
    public Cart(int bookID, int amount) {
        this.bookID = bookID;
        this.amount = amount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Cart(int bookID, String title, int amount, double price, int qty, int discount){
        this.bookID = bookID;
        this.title = title;
        this.amount = amount;
        this.price = price;
        this.qty = qty;
        this.discount = discount;
    }

    public int getBookID() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }
}
