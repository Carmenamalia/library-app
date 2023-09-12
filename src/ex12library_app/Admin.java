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

    //Atentie: toate metodele vor trebui sa primeasca ca parametru in plus o biblioteca

    public Admin(String name, Library library) {
        super(name, library);
    }

    //Adaug o noua carte in sistem (adica in biblioteca)
    //addBook() - va adauga o carte in lista de carti a bilbiotecii. Daca cartea cu acel ISBNCode exista deja in lista de carti a bibliotecii,
    //     atunci doar i se va incrementa valoarea atributului numberOfCopies
    public void addBook(Book newBook, int numberOfCopies) {
        Book[] booksList = getLibrary().getBooksList();
        boolean bookExists = false;
        //  Pentru fiecare carte în lista de cărți a bibliotecii:
        for (int i = 0; i < booksList.length; i++) {
            Book existingBook = booksList[i];
            //Cartea există deja, așa că adaug doar copii noi
            if (existingBook != null && existingBook.getISBNCode().equals(newBook.getISBNCode())) {
                existingBook.setTotalNumberOfCopies(existingBook.getTotalNumberOfCopies() + numberOfCopies);
                bookExists = true;
                System.out.println("Cartea exista in lista,s-au adaugat " + numberOfCopies + " copii");
                break;
            }
        }
        // Dacă cartea nu există,o adaug în lista de cărți a bibliotecii
        if (!bookExists) {
            for (int i = 0; i < booksList.length; i++) {
                if (booksList[i] == null) {
                    newBook.setTotalNumberOfCopies(numberOfCopies);
                    booksList[i] = newBook;
                    System.out.println("Cartea a fost adaugata si are " + numberOfCopies + " copii");
                    break;
                }
            }
        }
    }

    //deleteBook() - Va fi o metoda supraincarcata. Prima varianta va primi doar un paramentru, ISBNCode si va sterge de tot cartea din lista de
    // carti a bibliotecii. A doua varianta va primi doi paremetrii: ISBNCode si numarul de copii care se vor sterge. In acest caz, cartea nu va fi
    // stearsa din lista de carti a bibliotecii, ci doar se va scadea din numarul total de copii al cartii, numarul de copii primit ca parametru

    public boolean deleteBook(String ISBNCode) {
        Book[] booksList = getLibrary().getBooksList();
        for (int i = 0; i < booksList.length; i++) {
            // caut cartea cu codul ISBN
            if (booksList[i] != null && booksList[i].getISBNCode().equals(ISBNCode)) {
                // mut toate elementele din dreapta elementului găsit pentru a umple spațiul gol
                for (int j = i; j < booksList.length - i - 1; j++) {
                    booksList[j] = booksList[j + 1];
                }
                // setez ultimul element din tablou ca null pentru a șterge referința către cartea ștearsă
                booksList[booksList.length - 1] = null;
                System.out.println("Cartea a fost stearsa");
                return true;
            } else {
                System.out.println("cartea nu se afla in lista");
                return false;
            }
        }
        return true;
    }

    public boolean deleteBook(String ISBNCode, int numberOfCopies) {
        Book[] booksList = getLibrary().getBooksList();
        boolean bookFound = false;
        for (int i = 0; i < booksList.length; i++) {
            if (booksList[i] != null && (booksList[i].getISBNCode().equals(ISBNCode))) {
                bookFound = true;
                if (bookFound&&(numberOfCopies <= booksList[i].getTotalNumberOfCopies())){
                    booksList[i].setTotalNumberOfCopies(booksList[i].getTotalNumberOfCopies() - numberOfCopies);
                    System.out.println("S-au sters " + numberOfCopies + " de copii ale cartii " + booksList[i].getTitle());
                    return true;
                } else {
                    System.out.println("numarul de copii de sters e mai mare decat numarul de copii din biblioteca ");
                    return false;
                }
            }
        }
            if (!bookFound){
                System.out.println("cartea cu ISBNCode: " + ISBNCode + " nu exista in lista");
                return false;
            }

        return true;
    }

        //listAllBooks() - va printa detaliile fiecarei carti din lista de carti a bibliotecii
        public void listAllBooks() {
            Book[] booksList = getLibrary().getBooksList();
            for (int i = 0; i < booksList.length; i++) {
                System.out.println(booksList[i].getTitle());
            }
        }

        //listBookDetails() - Va printa detaliile unei singure carti din biblioteca, in functie de codul ISBNCode
        public void listBookDetails (String ISBNCode){
            Book[] booksList = getLibrary().getBooksList();
            for (int i = 0; i < booksList.length; i++) {
                System.out.println(booksList[i].toString());
            }
        }

        //viewBorrowedBooks() - Va lista codurile ISBNCode ale cartilor imprumutate de un client, in functie de numele clientului primit ca parametru.
        public void viewBorrowedBooks (String clientName){

            System.out.println("");
        }
    }
