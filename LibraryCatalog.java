
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/** A tiny in-memory library catalog for practicing Java object modeling. */
public class LibraryCatalog {
    private static final Scanner INPUT = new Scanner(System.in);
    private static final List<Book> BOOKS = new ArrayList<>();
    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            System.out.println("\n=== Library Catalog ===");
            System.out.println("1. Add book\n2. List books\n3. Search title or author\n0. Exit");
            switch (readInt("Choose: ")) {
                case 1 -> addBook();
                case 2 -> listBooks();
                case 3 -> searchBooks();
                case 0 -> running = false;
                default -> System.out.println("Choose one of the listed options.");
            }
        }
        System.out.println("Goodbye.");
    }
    private static void addBook() {
        System.out.print("Title: ");
        String title = INPUT.nextLine().trim();
        System.out.print("Author: ");
        String author = INPUT.nextLine().trim();
        if (title.isEmpty() || author.isEmpty()) { System.out.println("Both title and author are required."); return; }
        BOOKS.add(new Book(title, author));
        System.out.println("Book added to catalog.");
    }
    private static void listBooks() {
        if (BOOKS.isEmpty()) { System.out.println("Catalog is empty."); return; }
        for (int i = 0; i < BOOKS.size(); i++) System.out.printf("%d. %s%n", i + 1, BOOKS.get(i));
    }
    private static void searchBooks() {
        System.out.print("Search title or author: ");
        String query = INPUT.nextLine().trim().toLowerCase();
        if (query.isEmpty()) { System.out.println("Enter a search word."); return; }
        List<Book> matches = BOOKS.stream()
                .filter(book -> book.title.toLowerCase().contains(query) || book.author.toLowerCase().contains(query))
                .toList();
        if (matches.isEmpty()) System.out.println("No matching books.");
        else matches.forEach(System.out::println);
    }
    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try { return Integer.parseInt(INPUT.nextLine().trim()); }
            catch (NumberFormatException ex) { System.out.println("Please enter a whole number."); }
        }
    }
    private static final class Book {
        private final String title;
        private final String author;
        private Book(String title, String author) { this.title = title; this.author = author; }
        @Override public String toString() { return "\"" + title + "\" by " + author; }
    }
}
