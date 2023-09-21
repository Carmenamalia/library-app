package ex12library_app;

public class Admin extends User {

    public Admin(String name, Library library) {
        super(name, library);
    }

    // adauga o carte in lista de carti a bilbiotecii. Daca cartea cu acel ISBNCode exista deja in lista de carti a bibliotecii,
    //     atunci doar i se va incrementa valoarea atributului numberOfCopies
    public void addBook(Book newBook, int numberOfCopies) {
        Book existingBook = getLibrary().findBookByISBNCode(newBook.getISBNCode());
        if (existingBook != null) {
            //Cartea există deja, așa că adaug doar copii noi
            existingBook.setTotalNumberOfCopies(existingBook.getTotalNumberOfCopies() + numberOfCopies);
            System.out.println("Cartea cu codul ISBN: " + existingBook.getISBNCode() + "  exista in lista,s-au adaugat " + numberOfCopies + " copii");
        } else {
            // Dacă cartea nu există,o adaug în lista de cărți a bibliotecii
            getLibrary().addBookInList(newBook);
            newBook.setTotalNumberOfCopies(numberOfCopies);
            System.out.println("Cartea cu codul ISBN: " + newBook.getISBNCode() + " a fost adaugata si are " + numberOfCopies + " copii");
        }
    }

    public void deleteBook(String ISBNCode) throws Exception {
        // caut cartea cu codul ISBN  dupa index
        int index = getLibrary().findIndexOfBook(ISBNCode);
        //daca nu am gasit cartea arunc exceptie
        if (index == -1) {
            throw new Exception("cartea cu codul ISBN: " + ISBNCode + " nu este in lista");
        } else {
            // mut toate elementele din dreapta elementului la stanga
            getLibrary().deleteBook(index);
            System.out.println("Cartea cu codul ISBN: " + ISBNCode + " a fost stearsa");
        }
    }

    public void deleteBook(String ISBNCode, int numberOfCopiesToDelete) throws Exception {
        //caut cartea
        Book book = getLibrary().findBookByISBNCode(ISBNCode);
        //daca cartea nu exista arunc exceptie
        if (book == null) {
            throw new Exception("cartea cu codul ISBN: " + ISBNCode + " nu este in lista");
        }
        //updatez nr de copii al cartii din lista
        if (numberOfCopiesToDelete >= book.getTotalNumberOfCopies()) {
            throw new Exception("pentru cartea cu ISBN " + ISBNCode + "numarul de copii de sters e mai mare decat numarul de copii din biblioteca");
        }
        //obtin nr de copii ramase
        int remainingCopies = book.getTotalNumberOfCopies() - numberOfCopiesToDelete;
        book.setTotalNumberOfCopies(remainingCopies);
        System.out.println("S-au sters " + numberOfCopiesToDelete + " de copii ale cartii cu ISBN: " + book.getISBNCode());
        System.out.println("au mai ramas " + remainingCopies + " copii pentru cartea cu ISBN " + ISBNCode);
    }

    //printeaza detaliile fiecarei carti din lista de carti a bibliotecii
    public void listAllBooks() {
        getLibrary().listBookList();
    }

    //printeaza detaliile unei singure carti din biblioteca, in functie de codul ISBNCode
    public void listBookDetails(String ISBNCode) {
        System.out.println(getLibrary().findBookByISBNCode(ISBNCode));
    }

    //listeaza codurile ISBNCode ale cartilor imprumutate de un client, in functie de numele clientului primit ca parametru.
    public void viewBorrowedBooks(Client client) {
        for (int i = 0; i < client.getNumberOfBorrowedBooks(); i++) {
            System.out.println("clientul " + client.getName() + " a imprumutat cartea cu ISBN: " + client.getBorrowedBookCodes()[i]);
        }
    }
}


