package com.antoniocappiello.curriculumvitae.presenter.entityhandler;

import com.antoniocappiello.curriculumvitae.model.Book;

import java.util.List;

public class BookEntityOrchestrator {

    private BookValidator mValidator;
    private BookSaver mSaver;

    public BookEntityOrchestrator(BookValidator validator, BookSaver saver) {
        mValidator = validator;
        mSaver = saver;
    }

    public void save(Book book){
        if(mValidator.isValid(book)){
            mSaver.save(book);
        }
    }

    public void save(List<Book> bookList) {
        for(Book book: bookList){
            save(book);
        }
    }
}
