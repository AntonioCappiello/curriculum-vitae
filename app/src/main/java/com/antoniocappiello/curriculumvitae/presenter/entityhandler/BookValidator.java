/*
 * Created by Antonio Cappiello on 1/13/16 9:31 PM
 * Copyright (c) 2016. All rights reserved.
 *
 * Last modified 1/10/16 10:38 PM
 */

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
