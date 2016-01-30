/*
 * Created by Antonio Cappiello on 1/13/16 9:31 PM
 * Copyright (c) 2016. All rights reserved.
 *
 * Last modified 1/11/16 6:46 PM
 */

package com.antoniocappiello.curriculumvitae.presenter.entityhandler;

import com.antoniocappiello.curriculumvitae.model.Book;
import com.antoniocappiello.curriculumvitae.model.Book_Table;
import com.raizlabs.android.dbflow.sql.language.Condition;
import com.raizlabs.android.dbflow.sql.language.Select;

import java.util.List;

public class BookReader {

    public List<Book> readAll() {
        return new Select()
                .from(Book.class)
                .queryList();
    }

    public List<Book> readByName(String bookName) {
        return new Select()
                .from(Book.class)
                .where(Book_Table.name.is(bookName))
                .queryList();
    }

    public List<Book> readByAuthor(String author) {
        return new Select()
                .from(Book.class)
                .where(Book_Table.author.is(author))
                .queryList();
    }

    public boolean hasBookWithName(String bookName){
        return readByName(bookName).size() > 0;
    }
}
