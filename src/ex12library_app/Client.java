package ex12library_app;

public class Client extends User {
    /* Ca si client, am nevoie sa:
     **Vad o lista cu toate cartile disponibile pentru imprumut
     **Caut o carte in biblioteca si sa vad daca este disponibila
     **Imprumut o carte
     **Returnez o carte
   * Atentie: toate metodele vor trebui sa primeasca ca parametru in plus o biblioteca
     */

    private String[] borrowedBookCodes; //- lista ISBNCode-urilor cartilor imprumutate de client

    int numberOfBorrowedBooks;

    public Client(String name, Library library) {
        super(name, library);
        this.borrowedBookCodes = new String[10];
        this.numberOfBorrowedBooks = 0;
    }

    public String[] getBorrowedBookCodes() {
        return borrowedBookCodes;
    }

    public void setBorrowedBookCodes(String[] borrowedBookCodes) {
        this.borrowedBookCodes = borrowedBookCodes;
    }

    public int getNumberOfBorrowedBooks() {
        return numberOfBorrowedBooks;
    }

    public void setNumberOfBorrowedBooks(int numberOfBorrowedBooks) {
        this.numberOfBorrowedBooks = numberOfBorrowedBooks;
    }

    //isBookAvailable() - Va cauta o carte in biblioteca dupa ISBNCode si va afisa daca este sau nu disponibila pentru imprumut
    public boolean isBookAvailable(String ISBNCode){
        Book[] booksList = getLibrary().getBooksList();
        boolean bookExist = false;
        for (int i = 0; i < booksList.length; i++) {
            if (booksList[i].getISBNCode().equals(ISBNCode)) {
                bookExist = true;
            }
            if (bookExist&&booksList[i].getTotalNumberOfCopies()>=1){
                System.out.println("cartea cu ISBNCode: " + ISBNCode +" este disponibila");
                return true;
            } else {
                System.out.println("cartea cu ISBNCode: " + ISBNCode +" nu este disponibila");

            }
        }
        return true;
    }
    //viewAvailableBooks() - va afisa in consola toate cartile disponibile din biblioteca (adica cartile care au borrowedNumberOfBooks mai mic
    //decat totalNumberOfBooks)
    public void viewAvailableBooks(){

        System.out.println("");
    }
    //borrowBook() - va imprumuta o carte din biblioteca dupa ISBNCode, ceea ceva avea doua efecte: Codul cartii imprumutate va fi adaugat
    // in lista de coduri a clientului si In acelasi timp, cartii cu acelasi ISBNCode din lista de carti din librarie ii va fi incrementat
    // atributul borrowedNumberOfCopies cu 1. Atentie! Operatia de imprumutare nu va fi posibila pentru o carte daca borrowedNumberOfCopies
    // este egal cu totalNumberOfCopies
    public boolean borrowBook(String ISBNCode){
       return true;
    }
    //returnBook() - va returna o carte in biblioteca dupa ISBNCode, ceea ce va avea doua efecte: Codul ISBNCode va disparea din lista de coduri
    // ale clientului si In acelasi timp, cartii cu acel ISBNCode din lista de carti a bibliotecii ii va fi decrementat atributul
    // borrowedNumberOfCopies cu 1.
    public boolean returnBook(String ISBNCode){
        return true;
    }
}
