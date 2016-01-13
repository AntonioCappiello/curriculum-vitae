package com.antoniocappiello.curriculumvitae.presenter.parser;

import com.antoniocappiello.curriculumvitae.model.Book;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

public class BookJsonParser {
    public static List<Book> parse(JsonObject jsonObject) {
        List<Book> bookList = new ArrayList<>();
        JsonArray jsonArray = jsonObject.get("book").getAsJsonArray();
        for(JsonElement jsonElement:jsonArray){
            JsonObject bookAsJsonObject = jsonElement.getAsJsonObject();
            Book book = parseSingle(bookAsJsonObject);
            bookList.add(book);
        }
        return bookList;
    }

    private static Book parseSingle(JsonObject bookAsJsonObject) {
        return new Book(bookAsJsonObject.get("name").getAsString(),
                bookAsJsonObject.get("author").getAsString(),
                bookAsJsonObject.get("coverImageUrl").getAsString()
        );
    }
}
