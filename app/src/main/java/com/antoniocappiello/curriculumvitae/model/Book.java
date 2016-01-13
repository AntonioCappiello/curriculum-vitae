package com.antoniocappiello.curriculumvitae.model;


import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

@Table(database = Database.class)
public class Book extends BaseModel {

    @PrimaryKey(autoincrement = true)
    long id;

    @Column
    String name;

    @Column
    String author;

    @Column
    String coverImageUrl;

    public Book(){}

    public Book(String name, String author, String coverImageUrl) {
        this.name = name;
        this.author = author;
        this.coverImageUrl = coverImageUrl;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public String getCoverImageUrl() {
        return coverImageUrl;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", coverImageUrl='" + coverImageUrl + '\'' +
                '}';
    }
}
