package com.dreaminginprod.cache;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
final class ApplicationLauncher implements CommandLineRunner {
    private final BookRepository bookRepository;

    public ApplicationLauncher(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) {
        final var newBook1 = new NewBook("Author1", "Title1");
        final var newBook2 = new NewBook("Author2", "Title2");

        final var persistedBook1 = bookRepository.create(newBook1);
        final var persistedBook2 = bookRepository.create(newBook2);

        Assert.notNull(persistedBook1, "Expected non-null persisted book: " + newBook1);
        Assert.notNull(persistedBook2, "Expected non-null persisted book: " + newBook2);

        System.out.println("Retrieving book: " + persistedBook1.id());
        bookRepository.read(persistedBook1.id()).orElseThrow();
        System.out.println("Retrieving book: " + persistedBook2.id());
        bookRepository.read(persistedBook2.id()).orElseThrow();

        System.out.println("Retrieving book: " + persistedBook1.id());
        bookRepository.read(persistedBook1.id()).orElseThrow();
        System.out.println("Retrieving book: " + persistedBook2.id());
        bookRepository.read(persistedBook2.id()).orElseThrow();

        final var updatedBook = new Book(persistedBook1.id(), "NewTitle", "NewAuthor");

        System.out.println("Updating book: " + updatedBook);
        bookRepository.update(updatedBook);

        System.out.println("Retrieving book: " + updatedBook.id());
        bookRepository.read(updatedBook.id()).orElseThrow();
        System.out.println("Retrieving book: " + updatedBook.id());
        bookRepository.read(updatedBook.id()).orElseThrow();

        System.out.println("Deleting book: " + updatedBook);
        bookRepository.delete(updatedBook.id());

        System.out.println("Retrieving book: " + updatedBook.id());
        Assert.isTrue(
                bookRepository.read(updatedBook.id()).isEmpty(),
                "Book should be deleted: " + updatedBook
        );

        System.out.println("Retrieving book: " + updatedBook.id());
        Assert.isTrue(
                bookRepository.read(updatedBook.id()).isEmpty(),
                "Book should be deleted: " + updatedBook
        );

        System.out.println("Finished");
    }
}
