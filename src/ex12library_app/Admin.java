package ex12library_app;

public class Admin extends User {
    /* Ca si admin, am nevoie sa:
     **Adaug o noua carte in sistem (adica in biblioteca)
     **Sa sterg o noua carte din sistem cu totul
     **Sa sterg o copie a unei carti din sistem
     **Sa vad detaliile tututor cartilor din sistem
     **Sa caut o carte in sistem dupa codul ISBN si sa ii vad detaliile (inclusiv cate copii exista in total si cate sunt imprumutate)
     **Sa vad toate cartile imprumutate de un anumit client
     */

    public Admin(String name, Library library) {
        super(name, library);
    }

    //Adaug o noua carte in sistem (adica in biblioteca)
    //addBook() - va adauga o carte in lista de carti a bilbiotecii. Daca cartea cu acel ISBNCode exista deja in lista de carti a bibliotecii,
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

    //deleteBook() - Va fi o metoda supraincarcata. Prima varianta va primi doar un paramentru, ISBNCode si va sterge de tot cartea din lista de
    // carti a bibliotecii. A doua varianta va primi doi paremetrii: ISBNCode si numarul de copii care se vor sterge. In acest caz, cartea nu va fi
    // stearsa din lista de carti a bibliotecii, ci doar se va scadea din numarul total de copii al cartii, numarul de copii primit ca parametru

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
        int remainingCopies = book.getTotalNumberOfCopies() - numberOfCopiesToDelete;
        book.setTotalNumberOfCopies(remainingCopies);
        System.out.println("S-au sters " + numberOfCopiesToDelete + " de copii ale cartii cu ISBN: " + book.getISBNCode());
        System.out.println("au mai ramas " + remainingCopies + " copii pentru cartea cu ISBN " + ISBNCode);
    }

    //listAllBooks() - va printa detaliile fiecarei carti din lista de carti a bibliotecii
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


