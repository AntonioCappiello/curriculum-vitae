package com.antoniocappiello.curriculumvitae.presenter.entityhandler;

import com.antoniocappiello.curriculumvitae.model.Book;
import com.antoniocappiello.curriculumvitae.model.Book_Table;
import com.raizlabs.android.dbflow.sql.language.Condition;
import com.raizlabs.android.dbflow.sql.language.Select;

import java.util.List;

public class BookReader {

    public List<Book> read() {
        return new Select()
                .from(Book.class)
                .queryList();
    }

}
