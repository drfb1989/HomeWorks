package app;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LibraryTest {

        @Test
        void addBook_shouldIncreaseCount_onValidBook() {
            Library lib = new Library();
            lib.addBook(new Book("Clean Code", "Robert C. Martin"));
            assertEquals(1, lib.getBookCount());
            assertEquals(1, lib.getBooks().size());
        }

        @Test
        void addBook_shouldThrow_onNull() {
            Library lib = new Library();
            assertThrows(IllegalArgumentException.class, () -> lib.addBook(null));
        }

        @Test
        void addBook_shouldThrow_onInvalidFields() {
            Library lib = new Library();
            assertThrows(IllegalArgumentException.class, () -> lib.addBook(new Book("  ", "Author")));
            assertThrows(IllegalArgumentException.class, () -> lib.addBook(new Book("Title", "   ")));
        }

        @Test
        void addBook_shouldIgnoreDuplicates() {
            Library lib = new Library();
            Book b1 = new Book("DDD", "Eric Evans");
            Book b2 = new Book("DDD", "Eric Evans"); // equals() -> true

            lib.addBook(b1);
            lib.addBook(b2);

            assertEquals(1, lib.getBookCount());
            assertTrue(lib.getBooks().contains(b1));
        }

        @Test
        void removeBook_shouldReturnTrue_whenPresent() {
            Library lib = new Library();
            Book b = new Book("Refactoring", "Martin Fowler");
            lib.addBook(b);

            boolean removed = lib.removeBook(b);
            assertTrue(removed);
            assertEquals(0, lib.getBookCount());
        }

        @Test
        void removeBook_shouldReturnFalse_whenNullOrAbsent() {
            Library lib = new Library();
            Book b = new Book("TDD", "Kent Beck");

            assertFalse(lib.removeBook(null));
            assertFalse(lib.removeBook(b));

            lib.addBook(b);
            assertTrue(lib.removeBook(b));
            assertFalse(lib.removeBook(b)); // вдруге — вже немає
        }

        @Test
        void getBooks_shouldBeUnmodifiable() {
            Library lib = new Library();
            lib.addBook(new Book("Effective Java", "Joshua Bloch"));

            List<Book> view = lib.getBooks();
            assertThrows(UnsupportedOperationException.class, () -> view.add(new Book("X", "Y")));
        }

        @Test
        void book_toString_equals_hashCode_basicContracts() {
            Book a = new Book("Nineteen Eighty-Four", "George Orwell");
            Book b = new Book("Nineteen Eighty-Four", "George Orwell");
            Book c = new Book("Animal Farm", "George Orwell");

            assertEquals(a, b);
            assertEquals(a.hashCode(), b.hashCode());
            assertNotEquals(a, c);

            String s = a.toString();
            assertTrue(s.contains("title"));
            assertTrue(s.contains("author"));
        }
    }


