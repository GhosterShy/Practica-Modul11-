import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


abstract class User {
    protected int id;
    protected String name;
    protected String email;

    public User(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public abstract void register();
    public abstract void login();
}

// Класс Reader
class Reader extends User {
    public Reader(int id, String name, String email) {
        super(id, name, email);
    }

    public void borrowBook(Book book, Library library) {
        if (book.isAvailable()) {
            library.issueLoan(new Loan(book, this, LocalDate.now()));
            book.changeAvailabilityStatus(false);
            System.out.println("Borrowed book: " + book.getTitle());
        } else {
            System.out.println("Book is not available: " + book.getTitle());
        }
    }

    public void returnBook(Book book, Library library) {
        library.returnBook(book, this);
    }

    @Override
    public void register() {
        System.out.println("Reader registered.");
    }

    @Override
    public void login() {
        System.out.println("Reader logged in.");
    }
}

// Класс Librarian
class Librarian extends User {
    public Librarian(int id, String name, String email) {
        super(id, name, email);
    }

    public void addBook(Book book, Library library) {
        library.addBook(book);
        System.out.println("Book added: " + book.getTitle());
    }

    public void removeBook(Book book, Library library) {
        library.removeBook(book);
        System.out.println("Book removed: " + book.getTitle());
    }

    @Override
    public void register() {
        System.out.println("Librarian registered.");
    }

    @Override
    public void login() {
        System.out.println("Librarian logged in.");
    }
}

// Класс Author
class Author {
    private String name;
    private String biography;

    public Author(String name, String biography) {
        this.name = name;
        this.biography = biography;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}

// Класс Book
class Book {
    private String title;
    private String isbn;
    private List<Author> authors;
    private int publicationYear;
    private boolean availabilityStatus;

    public Book(String title, String isbn, List<Author> authors, int publicationYear) {
        this.title = title;
        this.isbn = isbn;
        this.authors = authors;
        this.publicationYear = publicationYear;
        this.availabilityStatus = true;
    }

    public String getTitle() {
        return title;
    }

    public boolean isAvailable() {
        return availabilityStatus;
    }

    public void changeAvailabilityStatus(boolean status) {
        this.availabilityStatus = status;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", isbn='" + isbn + '\'' +
                ", authors=" + authors +
                ", publicationYear=" + publicationYear +
                ", availabilityStatus=" + (availabilityStatus ? "Available" : "Not Available") +
                '}';
    }
}

// Класс Loan
class Loan {
    protected Book book;
    protected Reader reader;
    private LocalDate loanDate;
    private LocalDate returnDate;

    public Loan(Book book, Reader reader, LocalDate loanDate) {
        this.book = book;
        this.reader = reader;
        this.loanDate = loanDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    @Override
    public String toString() {
        return "Loan{" +
                "book=" + book.getTitle() +
                ", reader=" + reader.name +
                ", loanDate=" + loanDate +
                ", returnDate=" + returnDate +
                '}';
    }
}


class Library {
    private List<Book> books = new ArrayList<>();
    private List<User> users = new ArrayList<>();
    private List<Loan> loans = new ArrayList<>();

    public void addBook(Book book) {
        books.add(book);
    }

    public void removeBook(Book book) {
        books.remove(book);
    }

    public List<Book> searchBooks(String title) {
        return books.stream()
                .filter(book -> book.getTitle().equalsIgnoreCase(title))
                .toList();
    }

    public void issueLoan(Loan loan) {
        loans.add(loan);
    }

    public void returnBook(Book book, Reader reader) {
        for (Loan loan : loans) {
            if (loan.book.equals(book) && loan.reader.equals(reader)) {
                loan.setReturnDate(LocalDate.now());
                book.changeAvailabilityStatus(true);
                System.out.println("Book returned: " + book.getTitle());
                return;
            }
        }
        System.out.println("No matching loan found for book: " + book.getTitle());
    }

    public void generateReport() {
        System.out.println("Library Report:");
        System.out.println("Books in library:");
        for (Book book : books) {
            System.out.println(book);
        }
        System.out.println("Loans:");
        for (Loan loan : loans) {
            System.out.println(loan);
        }
    }
}


public class Main {
    public static void main(String[] args) {

        Author author1 = new Author("J.K. Rowling", "Британский писатель, наиболее известный благодаря серии о Гарри Поттере.");
        Author author2 = new Author("George R.R. Martin", "Американский писатель, автор «Игры престолов».");


        List<Author> authors1 = List.of(author1);
        Book book1 = new Book("Harry Potter", "12345", authors1, 1997);

        List<Author> authors2 = List.of(author2);
        Book book2 = new Book("Game of Thrones", "67890", authors2, 1996);


        Library library = new Library();


        Reader reader1 = new Reader(1, "Shyngys", "Shyngys@example.com");
        Librarian librarian = new Librarian(2, "Azamat", "Azamat@example.com");


        librarian.addBook(book1, library);
        librarian.addBook(book2, library);

        reader1.borrowBook(book1, library);
        reader1.returnBook(book1, library);

        library.generateReport();
    }
}


