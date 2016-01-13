/*
 * Created by Antonio Cappiello on 1/13/16 9:31 PM
 * Copyright (c) 2016. All rights reserved.
 *
 * Last modified 1/11/16 6:46 PM
 */

package com.antoniocappiello.curriculumvitae.presenter.entityhandler;

import com.antoniocappiello.curriculumvitae.model.Book;
import com.raizlabs.android.dbflow.sql.language.Select;

import java.util.List;

public class BookReader {

    public List<Book> read() {
        return new Select()
                .from(Book.class)
                .queryList();
    }

}
