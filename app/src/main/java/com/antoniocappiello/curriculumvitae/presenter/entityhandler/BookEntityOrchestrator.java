/*
 * Created by Antonio Cappiello on 1/13/16 9:31 PM
 * Copyright (c) 2016. All rights reserved.
 *
 * Last modified 1/13/16 6:05 PM
 */

package com.antoniocappiello.curriculumvitae.presenter.entityhandler;

import com.antoniocappiello.curriculumvitae.model.Book;
import com.orhanobut.logger.Logger;

import java.util.List;

public class BookEntityOrchestrator {

    private BookReader mBookReader;
    private BookValidator mBookValidator;
    private BookSaver mBookSaver;

    public BookEntityOrchestrator(BookReader bookReader, BookValidator bookValidator, BookSaver bookSaver) {
        mBookReader = bookReader;
        mBookValidator = bookValidator;
        mBookSaver = bookSaver;
    }

    public void save(Book book){
        if(!mBookReader.hasBookWithName(book.getName())){
            if (mBookValidator.isValid(book)) {
                mBookSaver.save(book);
                Logger.d("SAVED " + book.toString());
            }
            else {
                Logger.e("INVALID BOOK " + book.toString());
            }
        }
        else {
            Logger.e("BOOK WITH NAME " + book.getName() + " ALREADY SAVED");
        }
    }

    public void save(List<Book> bookList) {
        for(Book book: bookList){
            save(book);
        }
    }
}
