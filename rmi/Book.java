
package rmi;


public class Book {
     private String bookID, name, author, category, price;

    public Book() {
    }

    public Book(String bookID, String name, String author, String category, String price) {
        this.bookID = bookID;
        this.name = name;
        this.author = author;
        this.category = category;
        this.price = price;
    }

    public String getBookID() {
        return bookID;
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getKeyword() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Book{" + "bookID=" + bookID + ", name=" + name + ", author=" + author + ", category=" + category + ", price=" + price + '}';
    }
    
    public Object[] toArray()
    {
        return new Object[]{bookID,name,author,category,price};
    }
}
