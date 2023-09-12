package ex12library_app;

public class Library {
    private int numberOfBooks;
    private Book[] booksList;
    public Library(int size) {
        this.booksList = new Book[size];
    }


    public Book[] getBooksList() {
        return booksList;
    }

    public void setBooksList(Book[] booksList) {
        this.booksList = booksList;
    }

    public int getNumberOfBooks() {
        return numberOfBooks;
    }

    public void setNumberOfBooks(int numberOfBooks) {
        this.numberOfBooks = numberOfBooks;
    }
}
