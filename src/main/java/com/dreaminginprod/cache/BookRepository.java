package com.dreaminginprod.cache;

import jakarta.annotation.Nonnull;

import java.util.Optional;

public interface BookRepository {
    @Nonnull
    Book create(@Nonnull NewBook book);

    @Nonnull
    Optional<Book> read(@Nonnull String id);

    @Nonnull
    Book update(@Nonnull Book book);

    void delete(@Nonnull String id);
}
