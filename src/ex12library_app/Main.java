package ex12library_app;

public class Main {
    public static void main(String[] args) {
        //carti
        Book book1 = new Book("Maytrei", "Mircea Eliade", "10000");
        Book book2 = new Book("Colt Alb", "Jack London", "10001");
        Book book3 = new Book("Morometii", "Marin Preda", "10002");
        Book book4 = new Book("Ion", "Liviu Rebreanu", "10003");
        Book book5 = new Book("Baltagul", "Mihail Sadoveanu", "10004");

        //biblioteca cu lista de carti
        Library library = new Library(5);
        //clienti
        Client client1 = new Client("Amalia", library);
        Client client2 = new Client("Tudor", library);

        //admin
        Admin admin = new Admin("Marian", library);
        admin.addBook(book1, 5);
        admin.addBook(book2, 3);
        admin.addBook(book3, 4);
        admin.addBook(book4, 4);
        admin.addBook(book4, 3);
        admin.addBook(book5, 3);

        admin.deleteBook("10003");
        admin.deleteBook("10006");
        admin.deleteBook(book1.getISBNCode(), 2);
        admin.deleteBook("10002", 2);
        admin.deleteBook("10007", 2);
        admin.deleteBook("10004", 8);

        admin.addBook(book3, 4);

        admin.listAllBooks();
        admin.listBookDetails("10005");
        admin.viewBorrowedBooks("Amalia");
client1.isBookAvailable("10003");
    }
}
