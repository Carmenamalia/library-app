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

    private int numberOfBorrowedBooks;


    public Client(String name, Library library) {
        super(name, library);
        this.borrowedBookCodes = new String[5];
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
    public boolean isBookAvailable(String ISBNCode) {
        Book bookExist = getLibrary().findBookByISBNCode(ISBNCode);
        if (bookExist == null) {
            System.out.println("cartea cu ISBNCode: " + ISBNCode + " nu este disponibila");
            return false;
        } else if (bookExist.getTotalNumberOfCopies() >= 1) {
            System.out.println("cartea cu ISBNCode: " + ISBNCode + " este disponibila");
            return true;
        }
        return true;
    }


    //viewAvailableBooks() - va afisa in consola toate cartile disponibile din biblioteca (adica cartile care au borrowedNumberOfCopies mai mic
    //decat totalNumberOfCopies)
    public void viewAvailableBooks() {
        Book[] booksList = getLibrary().getBooksList();
        for (int i = 0; i < getLibrary().getNumberOfBooks(); i++) {
            int availableCopies = booksList[i].getTotalNumberOfCopies() - booksList[i].getBorrowedNumberOfCopies();
            if (availableCopies > 0) {
                System.out.println(booksList[i].toString() + " copii disponibile: " + availableCopies);
            }
        }
    }

    //borrowBook() - va imprumuta o carte din biblioteca dupa ISBNCode, ceea ce va avea doua efecte: Codul cartii imprumutate va fi adaugat
    // in lista de coduri a clientului si In acelasi timp, cartii cu acelasi ISBNCode din lista de carti din librarie ii va fi incrementat
    // atributul borrowedNumberOfCopies cu 1. Atentie! Operatia de imprumutare nu va fi posibila pentru o carte daca borrowedNumberOfCopies
    // este egal cu totalNumberOfCopies
    public void borrowBook(String ISBNCode,Client client) throws Exception {
        //varianta cu spargere in sub metode
        Book foundBook = getLibrary().findBookByISBNCode(ISBNCode);
        if (foundBook == null) {
            throw new Exception("Cartea cu ISBNCode: " + ISBNCode + " nu exista in lista");
        }
        if (!foundBook.isAvailable()) {
            throw new Exception("Cartea cu ISBNCode: " + ISBNCode + " nu este disponibila");
        }
       client.getBorrowedBookCodes()[numberOfBorrowedBooks] = foundBook.getISBNCode();
        numberOfBorrowedBooks++;
        client.setBorrowedBookCodes(getBorrowedBookCodes());
        //incrementez numărul de copii împrumutate
        foundBook.setBorrowedNumberOfCopies((foundBook.getBorrowedNumberOfCopies() + 1));
        //scad o copie din totalul de copii
        foundBook.setTotalNumberOfCopies((foundBook.getTotalNumberOfCopies() - 1));
        System.out.println("cartea cu ISBN: " + ISBNCode + " a fost imprumutata");
        //varianta fara spargere in sub metode
//        boolean isFound = false;
//        for (int i = 0; i < getLibrary().getNumberOfBooks(); i++) {
//            Book currentBook = getLibrary().getBooksList()[i];
//            if (currentBook.getISBNCode().equals(ISBNCode)) {
//                isFound = true;
//                if (currentBook.getBorrowedNumberOfCopies() < currentBook.getTotalNumberOfCopies()) {
//                    borrowedBookCodes[numberOfBorrowedBooks] = currentBook.getISBNCode();
//                    numberOfBorrowedBooks++;
        //incrementez numărul de copii împrumutate:
//                    currentBook.setBorrowedNumberOfCopies((currentBook.getBorrowedNumberOfCopies() + 1));

//                }else{
//                    throw new Exception("cartea nu este disponibila");
//                }
//            }return true;
//        }
//        if (!isFound) {
//            throw new Exception("Cartea cu ISBNCode: " + ISBNCode + " nu exista in lista");
//        }
//        return true;
    }

    public void deleteISBNCode(int startIndex) {
        for (int j = startIndex; j < numberOfBorrowedBooks - 1; j++) {
            borrowedBookCodes[j] = borrowedBookCodes[j + 1];
        }
        borrowedBookCodes[numberOfBorrowedBooks - 1] = null;
        numberOfBorrowedBooks--;
    }

    //returnBook() - va returna o carte in biblioteca dupa ISBNCode, ceea ce va avea doua efecte: Codul ISBNCode va disparea din lista de coduri
// ale clientului si In acelasi timp, cartii cu acel ISBNCode din lista de carti a bibliotecii ii va fi decrementat atributul
// borrowedNumberOfCopies cu 1.
    public void returnBook(String ISBNCode,Client client) throws Exception {
        Book foundBook = getLibrary().findBookByISBNCode(ISBNCode);
        //daca cartea nu este in lista de carti imprumutate ale clientului arunc exceptie
        for (int i = 0; i < client.getNumberOfBorrowedBooks(); i++) {
            if (!ISBNCode.equals(getBorrowedBookCodes()[i])) {
                throw new Exception("nu ai imprumutat cartea cu codul ISBN: " + ISBNCode);
            }
            if(ISBNCode.equals(getBorrowedBookCodes()[i])){
                //eliminam cartea din lista de carti imprumutate a clientului
                client.deleteISBNCode(i);
            }
        }
        //adaug cartea la totalul de copii
        foundBook.setTotalNumberOfCopies((foundBook.getTotalNumberOfCopies() + 1));
        System.out.println("cartea cu ISBN: " + ISBNCode + " a fost returnata");
    }
}

