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

    public Book findBookByISBNCode(String ISBNCode) {
        for (int i = 0; i < numberOfBooks; i++) {
            if (booksList[i].getISBNCode().equals(ISBNCode)) {
                return booksList[i]; // Cartea a fost găsită
            }
        }
        return null;
    }
    public int findIndexOfBook(String ISBNCode){
        //caut in lista de carti cartea dupa codul ISBN si returnez pozitia din lista a cartii
        for (int i = 0; i < numberOfBooks; i++) {
            if (booksList[i].getISBNCode().equals(ISBNCode)){
                return i;
            }

        }
        return -1;
    }

    public void deleteBook(int startIndex){
        //sterg cartea :mut cartea din dreapta in locul cartii de sters,la stanga
        for (int i = startIndex; i < numberOfBooks; i++) {
            booksList[i] = booksList[i+1];
        }
        //actualizez nr de carti
        numberOfBooks--;
    }
    public void addBookInList(Book book) {
        booksList[numberOfBooks] = book;
        numberOfBooks++;
    }
    public void listBookList(){
        for (int i = 0; i < numberOfBooks; i++) {
            System.out.println(booksList[i]);
        }
    }
}
