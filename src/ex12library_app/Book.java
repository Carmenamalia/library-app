package ex12library_app;

public class Book {
    private String Title;
    private String Author;
    private String ISBNCode;
    private int totalNumberOfCopies;
    private int borrowedNumberOfCopies;

    public Book(String title, String author, String ISBNCode) {
        Title = title;
        Author = author;
        this.ISBNCode = ISBNCode;

    }
    public String getTitle() {
        return Title;
    }
    public void setTitle(String title) {
        Title = title;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public String getISBNCode() {
        return ISBNCode;
    }

    public void setISBNCode(String ISBNCode) {
        this.ISBNCode = ISBNCode;
    }

    public int getTotalNumberOfCopies() {
        return totalNumberOfCopies;
    }

    public void setTotalNumberOfCopies(int totalNumberOfCopies) {
        this.totalNumberOfCopies = totalNumberOfCopies;
    }

    public int getBorrowedNumberOfCopies() {
        return borrowedNumberOfCopies;
    }


    public void setBorrowedNumberOfCopies(int borrowedNumberOfCopies) {
        this.borrowedNumberOfCopies = borrowedNumberOfCopies;
    }
    public boolean isAvailable(){
        return totalNumberOfCopies > borrowedNumberOfCopies;
    }

    @Override
    public String toString() {
        return "Book{" +
                "Title='" + Title + '\'' +
                ", Author='" + Author + '\'' +
                ", ISBNCode='" + ISBNCode + '\'' +
                ", totalNumberOfCopies=" + totalNumberOfCopies +
                ", borrowedNumberOfCopies=" + borrowedNumberOfCopies +
                '}';
    }
}
