import java.util.List;
import java.util.ArrayList;


class Book2 {
    private String title;
    private String author;
    private String genre;
    private String isbn;

    public Book2(String title, String author, String genre, String isbn) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    public String getIsbn() {
        return isbn;
    }

    @Override
    public String toString() {
        return "Book [title=" + title + ", author=" + author + ", genre=" + genre + ", isbn=" + isbn + "]";
    }
}

class Reader3 {
    private String firstName;
    private String lastName;
    private String ticketNumber;

    public Reader3(String firstName, String lastName, String ticketNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.ticketNumber = ticketNumber;
    }

    public String getTicketNumber() {
        return ticketNumber;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    @Override
    public String toString() {
        return "Reader [fullName=" + getFullName() + ", ticketNumber=" + ticketNumber + "]";
    }
}



class Librarian2 {
    private String name;

    public Librarian2(String name) {
        this.name = name;
    }

    public void issueBook(Book2 book, Reader3 reader) {
        System.out.println("Librarian " + name + " issued book: " + book.getTitle() + " to " + reader.getFullName());
        AccountSystem.recordBookIssue(book, reader);
    }

    public void returnBook(Book2 book, Reader3 reader) {
        System.out.println("Librarian " + name + " returned book: " + book.getTitle() + " from " + reader.getFullName());
        AccountSystem.recordBookReturn(book, reader);
    }
}

class Catalog {
    private List<Book2> books;

    public Catalog() {
        this.books = new ArrayList<>();
    }

    public void addBook(Book2 book) {
        books.add(book);
    }

    public Book2 searchByTitle(String title) {
        for (Book2 book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null;
    }

    public List<Book2> searchByAuthor(String author) {
        List<Book2> result = new ArrayList<>();
        for (Book2 book : books) {
            if (book.getAuthor().equalsIgnoreCase(author)) {
                result.add(book);
            }
        }
        return result;
    }

    public List<Book2> searchByGenre(String genre) {
        List<Book2> result = new ArrayList<>();
        for (Book2 book : books) {
            if (book.getGenre().equalsIgnoreCase(genre)) {
                result.add(book);
            }
        }
        return result;
    }
}

class AccountSystem {

    public static void recordBookIssue(Book2 book, Reader3 reader) {
        System.out.println("Recording book issue: " + book.getTitle() + " to " + reader.getFullName());
    }

    public static void recordBookReturn(Book2 book, Reader3 reader) {
        System.out.println("Recording book return: " + book.getTitle() + " from " + reader.getFullName());
    }
}







public class Component {
    public static void main(String[] args) {
        Book2 book1 = new Book2("Java Programming", "Darhan", "Programming", "123456");
        Book2 book2 = new Book2("Python Programming", "Daryn", "Programming", "654321");


        Reader3 reader1 = new Reader3("Shyngys", "Erzhurek", "R1001");
        Reader3 reader2 = new Reader3("Azamat", "Bolat", "R1002");


        Catalog catalog = new Catalog();
        catalog.addBook(book1);
        catalog.addBook(book2);


        System.out.println("Найти книгу 'Java Programming': " + catalog.searchByTitle("Java Programming"));
        System.out.println("Найти автора 'John Darhan': " + catalog.searchByAuthor("John Doe"));


        Librarian2 librarian = new Librarian2("Aibar");


        librarian.issueBook(book1, reader1);


        librarian.returnBook(book1, reader1);
    }
}
