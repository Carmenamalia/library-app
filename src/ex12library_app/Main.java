package ex12library_app;

public class Main {
    public static void main(String[] args) {
        //carti
        Book book1 = new Book("Maytrei", "Mircea Eliade", "10001");
        Book book2 = new Book("Colt Alb", "Jack London", "10002");
        Book book3 = new Book("Morometii", "Marin Preda", "10003");
        Book book4 = new Book("Ion", "Liviu Rebreanu", "10004");
        Book book5 = new Book("Baltagul", "Mihail Sadoveanu", "10005");

        //biblioteca cu lista de carti
        Library library = new Library(20);

        Client client1 = new Client("Amalia", library);
        Client client2 = new Client("Tudor", library);

        Admin admin = new Admin("Marian", library);
        System.out.println(" adauga cartile");
        admin.addBook(book1, 5);
        admin.addBook(book1, 6);
        admin.addBook(book2, 4);
        admin.addBook(book3, 3);
        admin.addBook(book4, 2);
        admin.addBook(book4, 3);
        admin.addBook(book5, 1);
        System.out.println("listeaza toate cartile ");
        admin.listAllBooks();
        System.out.println("e valabila cartea? ");
        client1.isBookAvailable("10002");
        try {
            System.out.println("sterge carti ");
            admin.deleteBook("10003");
            admin.deleteBook("10006");//aici am vrut sa testez exceptia
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            System.out.println("sterge copii ");
            admin.deleteBook("10001", 2);
            admin.deleteBook("10002", 5);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        admin.addBook(book3, 4);
        System.out.println("listeaza toate cartile");
        admin.listAllBooks();
        System.out.println("listeaza o carte ");
        admin.listBookDetails("10005");

        try {
            client1.isBookAvailable("10007");//aici am testat exceptia
            client1.isBookAvailable("10003");
            client2.isBookAvailable("10001");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            System.out.println("imprumuta o carte");
            client1.borrowBook("10001");
            client1.borrowBook("10002");
            client2.borrowBook("10001");
            client2.borrowBook("10003");
            client2.borrowBook("10004");
            client2.borrowBook("10002");
            client2.borrowBook("10005");
            client2.borrowBook("10001");//aici am testat limita de 5 carti imprumutate

            client2.borrowBook("10007");//am testat exceptia
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            System.out.println("returneaza cartea");

            client2.returnBook("10004");
            client2.listBorrowedBookCodes();//aici am testat sa vad daca s-a sters corect
            client1.returnBook("10001");
            client1.listBorrowedBookCodes();//aici am testat sa vad daca s-a sters corect
            client2.returnBook("10002");
            client2.returnBook("10001");
            client1.returnBook("10007");//am testat exceptia
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("toate cartile disponibile ");
        client2.viewAvailableBooks();
        System.out.println("cartile imprumutate de client ");
        admin.viewBorrowedBooks(client1);
        admin.viewBorrowedBooks(client2);
    }
}
