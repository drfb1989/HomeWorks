package app;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Library {
    private final List<Book> books = new ArrayList<>();


    public void addBook(Book book) {
        if (book == null) {
            throw new IllegalArgumentException("book must not be null");
        }
        if (book.getTitle() == null || book.getTitle().trim().isEmpty()
                || book.getAuthor() == null || book.getAuthor().trim().isEmpty()) {
            throw new IllegalArgumentException("book has invalid title/author");
        }
        if (!books.contains(book)) {
            books.add(book);
        }
    }
    public boolean removeBook(Book book) {
        if (book == null) return false;
        return books.remove(book);
    }
    public List<Book> getBooks() {
        return Collections.unmodifiableList(books);
    }
    public int getBookCount() {
        return books.size();
    }
}
