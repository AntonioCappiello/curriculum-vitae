package com.antoniocappiello.curriculumvitae.presenter.entityhandler;

import com.antoniocappiello.curriculumvitae.model.Book;

public class BookValidator {
    public boolean isValid(Book book) {
        return book != null &&
                book.getName() != null &&
                book.getAuthor() != null &&
                book.getCoverImageUrl() != null;
    }
}
